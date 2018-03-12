package com.example.alex.howmanymore.presentation.app;

import com.example.alex.howmanymore.presentation.activity.MainActivity;
import com.example.alex.howmanymore.presentation.dagger.MainActivityModule;
import com.example.alex.howmanymore.presentation.dagger.MainActivityPresenterModule;
import com.example.alex.howmanymore.presentation.dagger.SexPickerModule;
import com.example.alex.howmanymore.presentation.fragments.SexPickerFragment;
import com.example.alex.howmanymore.presentation.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex on 16.10.17.
 */

@Singleton
@Component(modules = {AppModule.class, MainActivityModule.class, MainActivityPresenterModule.class,
        SexPickerModule.class})
public interface AppComponent {
    void injectsActivity(MainActivity mainActivity);
    void injectsPresenter(MainActivityPresenter mainActivityPresenter);
    void injectsSexPicker(SexPickerFragment sexPickerFragment);
}
