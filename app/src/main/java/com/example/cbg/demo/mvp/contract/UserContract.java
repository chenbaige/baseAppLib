package com.example.cbg.demo.mvp.contract;

import android.app.Activity;

import com.example.cbg.demo.mvp.mode.entity.User;
import com.example.mylibrary.mvp.IModel;
import com.example.mylibrary.mvp.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;

public interface UserContract {

    interface View extends IView {
        void showUser(String user);

        //申请权限
        RxPermissions getRxPermissions();

        Activity getActivity();
    }


    interface Model extends IModel {
        Observable<String> getUser();
    }
}
