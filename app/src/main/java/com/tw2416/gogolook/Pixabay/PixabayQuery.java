package com.tw2416.gogolook.Pixabay;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Lamont on 2017/6/24.
 */

public interface PixabayQuery {
    @GET("api")
    Observable<PixabayResponse> search(@QueryMap Map<String, String> options);

}