package com.example.cbg.demo.mvp.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cbg.demo.R;
import com.example.cbg.demo.mvp.mode.entity.News;
import com.example.mylibrary.base.BaseHolder;
import com.example.mylibrary.di.component.AppComponent;
import com.example.mylibrary.imageloader.ImageConfigImpl;
import com.example.mylibrary.net.http.imageloader.ImageLoader;
import com.example.mylibrary.utils.CommonUtils;

import butterknife.BindView;

public class NewsItemHolder extends BaseHolder<News> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public NewsItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mAppComponent = CommonUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(@NonNull News data, int position) {
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
        mImageLoader.loadImage(itemView.getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getUrl())
                        .imageRadius(50)
                        .imageView(ivHead)
                        .build());

    }

    @Override
    protected void onRelease() {
        super.onRelease();
        this.tvContent = null;
        this.tvTitle = null;
    }
}
