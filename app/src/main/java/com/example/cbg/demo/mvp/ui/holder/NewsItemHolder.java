package com.example.cbg.demo.mvp.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.cbg.demo.R;
import com.example.cbg.demo.mvp.mode.entity.News;
import com.example.mylibrary.base.BaseHolder;

import butterknife.BindView;

public class NewsItemHolder extends BaseHolder<News> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public NewsItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(@NonNull News data, int position) {
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
    }

    @Override
    protected void onRelease() {
        super.onRelease();
        this.tvContent = null;
        this.tvTitle = null;
    }
}
