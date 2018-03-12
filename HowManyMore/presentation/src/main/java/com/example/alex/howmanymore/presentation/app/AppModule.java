package com.example.alex.howmanymore.presentation.app;

import android.content.Context;

import com.example.alex.howmanymore.domain.ResourceManager;
import com.example.alex.howmanymore.presentation.manager.AndroidResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 18.10.17.
 */

@Module
public class AppModule {
    private final Context context;
    private String codeLanguage;

    public AppModule(Context context, String codeLanguage) {
        this.context = context;
        this.codeLanguage = codeLanguage;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ResourceManager provideResourceManager() {
        return new AndroidResourceManager(context);
    }

    @Provides
    String provideCodeLanguage() {
        return codeLanguage;
    }
}
