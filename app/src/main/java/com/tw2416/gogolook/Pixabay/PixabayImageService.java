package com.tw2416.gogolook.Pixabay;

import com.tw2416.gogolook.IImage.IImageData;
import com.tw2416.gogolook.IImage.IImageService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lamont on 2017/6/27.
 */

public class PixabayImageService implements IImageService {
    @Override
    public Observable<List<IImageData>> createQueryImageObservable(String keyword) {
        return PixabayServiceManagers.getInstance().query(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<PixabayResponse, ObservableSource<List<IImageData>>>() {
                    @Override
                    public ObservableSource<List<IImageData>> apply(PixabayResponse response) throws Exception {
                        return Observable.just(PixabayImageData.createIImageDataList(response));
                    }
                });
    }
}
