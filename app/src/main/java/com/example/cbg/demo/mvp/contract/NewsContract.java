package com.example.cbg.demo.mvp.contract;

import android.app.Activity;

import com.example.mylibrary.mvp.IModel;
import com.example.mylibrary.mvp.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;

public interface NewsContract {

    interface View extends IView {

        Activity getActivity();
    }


    interface Model extends IModel {

    }
}
