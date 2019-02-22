package com.example.cbg.demo.mvp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbg.demo.R;
import com.example.cbg.demo.di.component.DaggerNewsComponent;
import com.example.cbg.demo.mvp.contract.NewsContract;
import com.example.cbg.demo.mvp.presenter.NewsPresenter;
import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.di.component.AppComponent;
import com.example.mylibrary.itegretion.EventBusManager;
import com.example.mylibrary.utils.CommonUtils;

import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ISupportFragment;

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View ,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @Inject
    RecyclerView.Adapter mAdapter;
    public static ISupportFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        recyclerView.setAdapter(mAdapter);
        mPresenter.setNewsList();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        CommonUtils.configRecyclerView(recyclerView, mLayoutManager);

        CommonUtils.makeText(mAppContext,"hello");
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void onRefresh() {

    }

}
