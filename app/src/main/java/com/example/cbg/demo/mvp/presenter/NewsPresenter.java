package com.example.cbg.demo.mvp.presenter;

import android.app.Application;
import android.support.v7.widget.RecyclerView;

import com.example.cbg.demo.mvp.contract.NewsContract;
import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.mode.entity.News;
import com.example.mylibrary.di.FragmentScope;
import com.example.mylibrary.itegretion.AppManager;
import com.example.mylibrary.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
public class NewsPresenter extends BasePresenter<NewsContract.Model, NewsContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;
    @Inject
    List<News> mNews;
    @Inject
    RecyclerView.Adapter mAdapter;

    @Inject
    public NewsPresenter(NewsContract.Model model, NewsContract.View rootView) {
        super(model, rootView);
    }

    public void setNewsList() {
        List<News> mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.setTitle("老虎教练");
            news.setContent("沙里夫-奥尼尔因心脏问题在去年离开了NCAA赛场，在之后成功接受了心脏手术。贾巴尔握住沙里夫的手告诉他：“如果你需要帮助，给我发短信，跟我聊聊进攻，我很乐意帮助你。");
            mDatas.add(news);
        }

        Observable.just(mDatas).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<List<News>>(mErrorHandler) {
            @Override
            public void onNext(List<News> news) {
                mNews.addAll(news);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
