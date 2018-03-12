package com.example.alex.howmanymore.presentation.presenter;

import android.content.Context;

import com.example.alex.howmanymore.presentation.activity.IView;

/**
 * Created by alex on 03.10.17.
 */

public abstract class PresenterBase<T extends IView> implements IPresenter<T> {
    private T view;
    private Context context;

    @Override
    public void attachView(T iView) {
        view = iView;
    }

    @Override
    public void detachView() {
        context = null;
        view = null;
    }

    protected T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }
}
