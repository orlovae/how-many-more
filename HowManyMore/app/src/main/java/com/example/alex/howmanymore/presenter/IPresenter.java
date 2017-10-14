package com.example.alex.howmanymore.presenter;

import android.content.Context;

import com.example.alex.howmanymore.activity.IView;

/**
 * Created by alex on 10.07.17.
 */

public interface IPresenter<V extends IView> {
    void attachView(V iView);
    void viewIsReady(Context context);
    void detachView();
    void destroy();
}
