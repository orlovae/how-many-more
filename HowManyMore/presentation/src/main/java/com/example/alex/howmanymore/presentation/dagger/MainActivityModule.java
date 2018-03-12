package com.example.alex.howmanymore.presentation.dagger;

import com.example.alex.howmanymore.presentation.presenter.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 16.10.17.
 */

@Module
public class MainActivityModule {

    @Provides
    MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }
}
