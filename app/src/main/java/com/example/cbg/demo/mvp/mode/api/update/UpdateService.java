package com.example.cbg.demo.mvp.mode.api.update;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface UpdateService {

    @GET("/")
    Observable<String> getLastestAppVersion(@QueryMap Map<String,String> update);

    @POST("/")
    Observable<String> postLastestAppVersion(@Body Map<String,String> update);

    @Streaming//下载大文件时需要加上
    @GET
    Observable  download();



}
