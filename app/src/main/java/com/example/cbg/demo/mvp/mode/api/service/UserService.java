package com.example.cbg.demo.mvp.mode.api.service;

import com.example.cbg.demo.mvp.mode.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("/")
    Observable<String> getUsers();

}
