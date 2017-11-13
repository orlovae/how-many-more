package com.example.alex.howmanymore.app;

import android.content.Context;
import android.test.suitebuilder.annotation.Suppress;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 18.10.17.
 */

@Module
public class AppModule {
    private final Context mContext;
    private String mCodeLanguage;

    public AppModule(Context context, String codeLanguage) {
        mContext = context;
        mCodeLanguage = codeLanguage;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    String provideCodeLanguage() {
        return mCodeLanguage;
    }
}
