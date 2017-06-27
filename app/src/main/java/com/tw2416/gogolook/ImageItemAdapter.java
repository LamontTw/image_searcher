package com.tw2416.gogolook;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tw2416.gogolook.IImage.IImageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lamont on 2017/6/26.
 */

public class ImageItemAdapter extends RecyclerView.Adapter<ImageHolder> {

    private final List<IImageData> mImageList = new ArrayList<>();

    public ImageItemAdapter() {

    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ImageHolder.generateImageHolder(parent);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        if (position >= 0 && position < mImageList.size()) {
            holder.bindView(mImageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public void setImageResultList(List<IImageData> imgData) {
        mImageList.clear();
        if (imgData != null) {
            mImageList.addAll(imgData);
        }

        notifyDataSetChanged();
    }


}