package com.example.cbg.demo.mvp.presenter;

import android.app.Application;

import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.mode.entity.User;
import com.example.mylibrary.di.ActivityScope;
import com.example.mylibrary.itegretion.AppManager;
import com.example.mylibrary.mvp.BasePresenter;
import com.example.mylibrary.utils.PermissionUtil;
import com.example.mylibrary.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;

    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View rootView) {
        super(model, rootView);
    }

    public void requestUsers(final boolean pullToRefresh) {
        //请求外部存储权限用于适配android6.0的权限管理机制
//        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
//            @Override
//            public void onRequestPermissionSuccess() {
//                //request permission success, do something.
//                requestFromModel(pullToRefresh);
//            }
//
//            @Override
//            public void onRequestPermissionFailure(List<String> permissions) {
//                mRootView.showMessage("Request permissions failure");
//                mRootView.hideLoading();//隐藏下拉刷新的进度条
//            }
//
//            @Override
//            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
//                mRootView.showMessage("Need to go to the settings");
//                mRootView.hideLoading();//隐藏下拉刷新的进度条
//            }
//        }, mRootView.getRxPermissions(), mErrorHandler);

        requestFromModel(pullToRefresh);
    }


    private void requestFromModel(boolean pullToRefresh) {

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        mModel.getUser()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.startLoadMore();//显示上拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
//                    else
//                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String user) {
                        mRootView.showUser(user);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
