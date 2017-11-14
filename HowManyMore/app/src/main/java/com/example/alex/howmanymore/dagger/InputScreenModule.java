package com.example.alex.howmanymore.dagger;

import android.content.Context;

import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.presenter.InputScreenPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 16.10.17.
 */

@Module
public class InputScreenModule {
    private DatabaseAdapter mDataBaseAdapter;

    @Singleton
    @Provides
    InputScreenPresenter provideInputScreenPresenter() {
        return new InputScreenPresenter();
    }

    @Singleton
    @Provides
    DatabaseAdapter provideDatabaseAdapter(Context context, String codeLanguage) {
        mDataBaseAdapter = new DatabaseAdapter(context, codeLanguage);
        return mDataBaseAdapter;
    }

    @Singleton
    @Provides
    List<Country> provideListCountry() {
        return mDataBaseAdapter.getCountries();
    }
}
