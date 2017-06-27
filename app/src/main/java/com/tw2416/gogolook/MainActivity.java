package com.tw2416.gogolook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.widget.TextView;

import com.tw2416.gogolook.IImage.IImageData;
import com.tw2416.gogolook.IImage.IImageService;
import com.tw2416.gogolook.Pixabay.PixabayImageService;
import com.tw2416.gogolook.util.Logger;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_edit_text)
    EditText inputEditText;
    @BindView(R.id.search_btn)
    TextView searchBtn;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_hint)
    TextView tv_hint;

    private ImageItemAdapter mImageItemAdapter;
    private IImageService mImgLoader;
    private Disposable mSearchDisposable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgLoader = new PixabayImageService();

        // setup ButterKnife
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // setup recycler view
        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mImageItemAdapter  = new ImageItemAdapter();
        mRecyclerView.setAdapter(mImageItemAdapter);

    }

    private void cancelSearch(String hint) {
        tv_hint.setVisibility(View.VISIBLE);
        if (mSearchDisposable != null) {
            mSearchDisposable.dispose();
            mSearchDisposable = null;
        }
        tv_hint.setText(hint);
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {
        mImageItemAdapter.setImageResultList(null);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputEditText.getWindowToken(), 0);
        cancelSearch("searching...");
        mRecyclerView.setVisibility(View.GONE);
        mImgLoader.createQueryImageObservable(inputEditText.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<IImageData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSearchDisposable = d;
                    }

                    @Override
                    public void onNext(List<IImageData> imgList) {
                        Logger.d("onNext" + imgList.size());
                        mRecyclerView.setVisibility(View.VISIBLE);
                        tv_hint.setVisibility(View.GONE);
                        mImageItemAdapter.setImageResultList(imgList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelSearch("onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @OnTextChanged(value = R.id.input_edit_text, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        searchBtn.setEnabled(!TextUtils.isEmpty(s.toString()));

    }

    @OnEditorAction(value = R.id.input_edit_text)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE && !TextUtils.isEmpty(inputEditText.getText().toString())) {
            searchBtn.performClick();
            return true;
        }
        return false;
    }


}
