/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cbg.demo.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cbg.demo.mvp.contract.NewsContract;
import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.mode.NewsModel;
import com.example.cbg.demo.mvp.mode.UserModel;
import com.example.cbg.demo.mvp.mode.entity.News;
import com.example.cbg.demo.mvp.ui.adapter.NewsAdapter;
import com.example.mylibrary.di.ActivityScope;
import com.example.mylibrary.di.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * 展示 Module 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.5">Module wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 11:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Module
public abstract class NewsModule {

    @Binds
    abstract NewsContract.Model bindNewsModel(NewsModel model);

    @FragmentScope
    @Provides
    static RxPermissions provideRxPermissions(NewsContract.View view) {
        return new RxPermissions((FragmentActivity) view.getActivity());
    }

    @FragmentScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(NewsContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @FragmentScope
    @Provides
    static List<News> provideNewsList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static RecyclerView.Adapter provideUserAdapter(Context context,List<News> list){
        return new NewsAdapter(context,list);
    }

    @FragmentScope
    @Provides
    static Context provideContext(NewsContract.View view){
        return view.getActivity();
    }

}
