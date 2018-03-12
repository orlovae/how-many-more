package com.example.alex.howmanymore.presentation.presenter;

import android.content.Context;
import android.graphics.Point;

import com.example.alex.howmanymore.presentation.activity.IView;

/**
 * Created by alex on 10.07.17.
 */

public interface IPresenter<V extends IView> {
    void attachView(V iView);
    void viewIsReady(int widthScreen, int heightAllDraw);
    void detachView();
    void destroy();
}
