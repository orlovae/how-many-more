package com.example.alex.howmanymore.app;

import android.app.Application;
import android.util.Log;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.dagger.InputScreenModule;
import com.example.alex.howmanymore.dagger.MainActivityModule;

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
                inputScreenModule(new InputScreenModule()).
                mainActivityModule(new MainActivityModule()).
                build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    private String getCodeLanguage() {
        Log.d(TAG, "getCodeLanguage: " + Locale.getDefault().getLanguage());
        String codeLanguage = null;

        switch (Locale.getDefault().getLanguage().toUpperCase()) {
            case Constants.RU:
                codeLanguage =Constants.RU;
                break;
            case Constants.EN:
                codeLanguage = Constants.EN;
                break;
        }
        Log.d(TAG, "codeLanguage: " + codeLanguage);
        return codeLanguage;
    }
}
