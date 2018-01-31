package com.lanjian.netdemo.net;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author lanjian
 * @email 819715035@qq.com
 * creat at $date$
 * description
 */
public interface API {
    String path = "course_api/";
    String BASE_URL = "http://112.124.22.238:8081/";

    @FormUrlEncoded
    @POST(path + "banner/query")
    Observable<List<Banner>> getBanner(@Field("type") int type);
}
