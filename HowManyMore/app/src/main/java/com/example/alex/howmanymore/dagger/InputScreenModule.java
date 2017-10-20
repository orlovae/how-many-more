package com.example.alex.howmanymore.dagger;

import android.content.Context;

import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.presenter.InputScreenPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 16.10.17.
 */

@Module
public class InputScreenModule {

    @Singleton
    @Provides
    InputScreenPresenter provideInputScreenPresenter() {
        return new InputScreenPresenter();
    }

    @Singleton
    @Provides
    DatabaseAdapter provideDatabaseAdapter(Context context) {
        return new DatabaseAdapter(context);
    }
}
