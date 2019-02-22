package com.example.cbg.demo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbg.demo.R;
import com.example.cbg.demo.di.component.DaggerUserComponent;
import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.presenter.UserPresenter;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.yokeyword.fragmentation.ISupportFragment;

public class RegisterFragment extends BaseFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.tv_register)
    TextView tvRegister;

    public static ISupportFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerUserComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        RetrofitUrlManager.getInstance().putDomain("github", "https://github.com");
        mPresenter.requestUsers(true);
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @OnClick(R.id.tv_register)
    public void onViewClicked() {
        start(NewsFragment.newInstance());
    }

    @Override
    public void showUser(String user) {
        tvRegister.setText(user);
    }

    @Override
    public RxPermissions getRxPermissions() {
        return null;
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
