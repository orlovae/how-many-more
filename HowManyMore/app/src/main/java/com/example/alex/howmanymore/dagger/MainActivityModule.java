package com.example.alex.howmanymore.dagger;

import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 16.10.17.
 */

@Module
public class MainActivityModule {

    @Provides
    MainActivityPresenter providemainActivityPresenter() {
        return new MainActivityPresenter();
    }
}
