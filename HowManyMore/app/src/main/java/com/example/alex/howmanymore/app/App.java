package com.example.alex.howmanymore.app;

import android.app.Application;
import android.util.Log;

import com.example.alex.howmanymore.constants.Keys;
import com.example.alex.howmanymore.dagger.MainActivityModule;
import com.example.alex.howmanymore.dagger.SexPickerModule;

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
