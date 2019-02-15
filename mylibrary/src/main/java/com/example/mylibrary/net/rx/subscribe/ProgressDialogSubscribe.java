package com.example.mylibrary.net.rx.subscribe;

import com.example.mylibrary.base.IProgressView;
import com.example.mylibrary.net.rx.ProgressDialogHandler;

import io.reactivex.disposables.Disposable;

/**
 * Title: basicmvpframwork
 * <p>
 * Description:
 * <p>
 * Author:baigege (baigegechen@gmail.com)
 * <p>
 * Date:2017-05-25
 */
public abstract class ProgressDialogSubscribe<T> extends ErrorSubscribe<T> implements ProgressDialogHandler.onProgressCancelistener {

    private ProgressDialogHandler mDialogHandler;

    private Disposable mDisposable;

    private IProgressView mView;


    public ProgressDialogSubscribe(IProgressView view) {
        super(view.getContext());
        this.mView = view;
        this.mDialogHandler = new ProgressDialogHandler(view.getContext(), true, this);
    }

    public boolean isCancel() {
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
        mView.showLoading();
        mDialogHandler.showDialog();
    }

    @Override
    public void onNext(T t) {
        if (null == t) {
            mView.showEmpty();
        } else {
            mView.showContent();
            onShowData(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.showError();
        mDialogHandler.dismissDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
//        mView.showContent();
        mDialogHandler.dismissDialog();
    }

    @Override
    public void onProgressCancel() {
        mDisposable.dispose();
    }

    public abstract void onShowData(T t);
}
