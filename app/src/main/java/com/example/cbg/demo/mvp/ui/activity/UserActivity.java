package com.example.cbg.demo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.cbg.demo.R;
import com.example.cbg.demo.mvp.mode.api.update.UpdateAppHttpUtil;
import com.example.cbg.demo.mvp.ui.fragment.RegisterFragment;
import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.di.component.AppComponent;
import com.example.mylibrary.update.UpdateAppManager;

public class UserActivity extends BaseActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerUserComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (findFragment(RegisterFragment.class) == null) {
            loadRootFragment(R.id.fl_container, RegisterFragment.newInstance());  //load root Fragment
        }
        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(this)
                .setPost(false)
                //更新地址
                .setUpdateUrl("http://www.baidu.com")
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .update();
    }


}
