package com.example.alex.howmanymore.app;

import com.example.alex.howmanymore.activity.MainActivity;
import com.example.alex.howmanymore.dagger.MainActivityModule;
import com.example.alex.howmanymore.dagger.SexPickerModule;
import com.example.alex.howmanymore.fragments.SexPickerFragment;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex on 16.10.17.
 */

@Singleton
@Component(modules = {AppModule.class, MainActivityModule.class, SexPickerModule.class})
public interface AppComponent {
    void injectsActivity(MainActivity mainActivity);
    void injectsPresenter(MainActivityPresenter mainActivityPresenter);
    void injectsSexPicker(SexPickerFragment sexPickerFragment);
}
