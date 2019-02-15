package com.example.cbg.demo.mvp.ui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cbg.demo.R;
import com.example.cbg.demo.di.component.DaggerUserComponent;
import com.example.cbg.demo.di.component.UserComponent;
import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.presenter.UserPresenter;
import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View {

    private TextView mTv;

    @Override
    public void showUser(String user) {
        mTv.setText(user);
    }

    @Override
    public RxPermissions getRxPermissions() {
        return null;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUserComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTv = findViewById(R.id.tv_content);
        mPresenter.requestUsers(true);

    }

    @Override
    public void showMessage(@NonNull String message) {
        mTv.setText(message);
    }
}
