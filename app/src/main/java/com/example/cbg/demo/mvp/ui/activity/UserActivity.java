package com.example.cbg.demo.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.cbg.demo.R;
import com.example.cbg.demo.di.component.DaggerUserComponent;
import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.presenter.UserPresenter;
import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.di.component.AppComponent;
import com.example.mylibrary.utils.CommonUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    public void showUser(String user) {
        tvContent.setText(user);
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
        mPresenter.requestUsers(true);
        System.out.println("ceshigit-v0");
    }

    @Override
    public void showMessage(@NonNull String message) {
        tvContent.setText(message);
    }

    @OnClick(R.id.tv_content)
    public void onViewClicked() {
        CommonUtils.makeText(UserActivity.this,"hello");
    }
}
