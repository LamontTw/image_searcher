package com.tw2416.gogolook.Pixabay;

import com.tw2416.gogolook.IImage.IImageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lamont on 2017/6/27.
 */

public class PixabayImageData implements IImageData{

    public PixabayHit mPxHit;
    public PixabayImageData(PixabayHit hit) {
        mPxHit = hit;
    }


    @Override
    public String getUrl() {
        return mPxHit.getWebformatURL();
    }

    public static List<IImageData> createIImageDataList(PixabayResponse response) {
        List<IImageData> result = new ArrayList<>();
        if (response != null
                && response.getHits() != null
                && response.getHits().size() > 0) {
            for (PixabayHit pxHit : response.getHits()) {
                result.add(new PixabayImageData(pxHit));
            }
        }
        return result;
    }
}
