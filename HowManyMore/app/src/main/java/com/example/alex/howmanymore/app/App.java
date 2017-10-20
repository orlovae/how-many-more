package com.example.alex.howmanymore.app;

import android.app.Application;

import com.example.alex.howmanymore.dagger.InputScreenModule;
import com.example.alex.howmanymore.dagger.MainActivityModule;

/**
 * Created by alex on 06.07.17.
 */

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().
                appModule(new AppModule(getBaseContext())).
                inputScreenModule(new InputScreenModule()).
                mainActivityModule(new MainActivityModule()).
                build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
