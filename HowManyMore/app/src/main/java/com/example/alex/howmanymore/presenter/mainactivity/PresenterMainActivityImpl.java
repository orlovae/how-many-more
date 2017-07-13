package com.example.alex.howmanymore.presenter.mainactivity;

import android.content.Context;

import com.example.alex.howmanymore.activity.mainactivity.IMainActivityView;

/**
 * Created by alex on 13.07.17.
 */

public class PresenterMainActivityImpl implements IMainActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private IMainActivityView view;
    private Context context;

    public PresenterMainActivityImpl(IMainActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }
}
