package com.example.alex.howmanymore.app;

import com.example.alex.howmanymore.activity.InputScreen;
import com.example.alex.howmanymore.activity.MainActivity;
import com.example.alex.howmanymore.dagger.InputScreenModule;
import com.example.alex.howmanymore.dagger.MainActivityModule;
import com.example.alex.howmanymore.presenter.InputScreenPresenter;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex on 16.10.17.
 */

@Singleton
@Component(modules = {AppModule.class, InputScreenModule.class, MainActivityModule.class})
public interface AppComponent {
    void injectsActivity(InputScreen inputScreen);
    void injectsActivity(MainActivity mainActivity);
    void injectsPresenter(InputScreenPresenter inputScreenPresenter);
    void injectsPresenter(MainActivityPresenter mainActivityPresenter);
}
