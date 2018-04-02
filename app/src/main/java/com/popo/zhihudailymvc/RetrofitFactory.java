package com.popo.zhihudailymvc;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lgp on 2018/3/30.
 */

public class RetrofitFactory {

    private static ApiService mAPIService;
    public static ApiService getAPIService() {
        if (mAPIService == null) {
            synchronized (RetrofitFactory.class) {
                if (mAPIService == null) {
                    mAPIService = new Retrofit.Builder()
                            .baseUrl(ApiService.BASEURL)
                            // 添加Gson转换器
                            .addConverterFactory(GsonConverterFactory.create())
                            // 添加Retrofit到RxJava的转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                            .create(ApiService.class);
                }
            }
        }
        return mAPIService;
    }
}
