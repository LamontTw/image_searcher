package com.tw2416.gogolook.Pixabay;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cm on 2017/6/24.
 */


public class PixabayServiceManagers {

    private static final String KEY = "5716557-5699d8a183b0786d19ef28a41";
    private static final String BASE_URL = "https://pixabay.com/";
    private static String PARAMETER_KEY = "key";
    private static String PARAMETER_KEYWORD = "q";

    private PixabayQuery pixabayQuery;
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient.Builder okHttpClientBuilder;

    private static PixabayServiceManagers Instance = new PixabayServiceManagers();

    public static PixabayServiceManagers getInstance() {
        return Instance;
    }

    private PixabayServiceManagers() {
        retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String requestUrl = chain.request().url().toString();
                String url = requestUrl.substring(BASE_URL.length());
                Request newRequest = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            }
        };

        okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor);

        pixabayQuery = createService(PixabayQuery.class);

    }


    public <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = retrofitBuilder
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public PixabayQuery getPixabayQuery() {
        return pixabayQuery;
    }

    public  Observable<PixabayResponse> query(@NonNull String... keywords) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(PARAMETER_KEY, KEY);
        StringBuffer keywordBuilder = new StringBuffer();
        String prefix = "";
        for (int i =  0; i < keywords.length; i ++) {
            if (!TextUtils.isEmpty(keywords[i])) {
                String[] splitWords = keywords[i].split(" ");
                for (int j =  0; j < splitWords.length; j ++) {
                    keywordBuilder.append(prefix);
                    keywordBuilder.append(splitWords[j]);
                    prefix = "+";
                }
            }
        }
        queryMap.put(PARAMETER_KEYWORD, keywordBuilder.toString());
        return getPixabayQuery().search(queryMap);
    }

}