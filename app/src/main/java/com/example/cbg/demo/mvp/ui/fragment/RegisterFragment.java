package com.example.cbg.demo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbg.demo.R;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.di.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ISupportFragment;

public class RegisterFragment extends BaseFragment {

    public static ISupportFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @OnClick(R.id.tv_register)
    public void onViewClicked() {
        start(NewsFragment.newInstance());
    }
}
