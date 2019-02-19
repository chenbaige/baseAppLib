package com.example.cbg.demo.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.cbg.demo.R;
import com.example.cbg.demo.mvp.mode.entity.News;
import com.example.cbg.demo.mvp.ui.holder.NewsItemHolder;
import com.example.mylibrary.base.BaseHolder;
import com.example.mylibrary.base.DefaultAdapter;

import java.util.List;

public class NewsAdapter extends DefaultAdapter<News> {

    public NewsAdapter(Context context, List<News> infos) {
        super(context, infos);
    }

    @NonNull
    @Override
    public BaseHolder<News> getHolder(@NonNull View v, int viewType) {
        return new NewsItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_fragment_news;
    }
}
