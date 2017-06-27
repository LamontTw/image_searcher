package com.tw2416.gogolook.IImage;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Lamont on 2017/6/26.
 */

public interface IImageService {
    Observable<List<IImageData>> createQueryImageObservable(String keyword);
}
