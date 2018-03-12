package com.example.alex.howmanymore.presentation.app;

import android.app.Application;
import android.util.Log;

import com.example.alex.howmanymore.presentation.constants.Keys;
import com.example.alex.howmanymore.presentation.dagger.MainActivityModule;
import com.example.alex.howmanymore.presentation.dagger.MainActivityPresenterModule;
import com.example.alex.howmanymore.presentation.dagger.SexPickerModule;

import java.util.Locale;

/**
 * Created by alex on 06.07.17.
 */

public class App extends Application {
    private static AppComponent component;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().
                appModule(new AppModule(getBaseContext(), getCodeLanguage())).
                mainActivityModule(new MainActivityModule()).
                mainActivityPresenterModule(new MainActivityPresenterModule(getBaseContext())).
                sexPickerModule(new SexPickerModule()).
                build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    private String getCodeLanguage() {
        Log.d(TAG, "getCodeLanguage: " + Locale.getDefault().getLanguage());
        String codeLanguage = null;

        switch (Locale.getDefault().getLanguage().toUpperCase()) {
            case Keys.RU:
                codeLanguage = Keys.RU;
                break;
            case Keys.EN:
                codeLanguage = Keys.EN;
                break;
        }
        Log.d(TAG, "codeLanguage: " + codeLanguage);
        return codeLanguage;
    }
}
