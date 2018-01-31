package com.lanjian.netdemo.net;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lanjian
 * @email 819715035@qq.com
 * creat at $date$
 * description
 */
public class NetClient {
    private static API api;
    private static Activity mContext;

    public static API getRetrofit(){
        if (api==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .client(getOkhttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            api = retrofit.create(API.class);
        }
        return api;
    }

    public static OkHttpClient getOkhttpClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(
                        new BaseInterceptor.Builder()
                                .addHeaderParamsMap(getHeaderMap())
                                .build())
                .build();
        return client;
    }

    private static Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json; charset=UTF-8");
        map.put("Connection", "application/json; charset=UTF-8");
        map.put("Accept", "*/*");
        return map;
    }
}
