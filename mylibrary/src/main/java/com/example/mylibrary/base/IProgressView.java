package com.example.mylibrary.base;

import android.app.Activity;

public interface IProgressView {

    void showLoading();

    void showEmpty();

    void showContent();

    void showError();

    Activity getContext();

}
