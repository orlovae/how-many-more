package com.example.alex.howmanymore.dagger;

import android.content.Context;

import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 16.10.17.
 */

@Module
public class MainActivityModule {
    private DatabaseAdapter mDataBaseAdapter;

    @Provides
    MainActivityPresenter providemainActivityPresenter() {
        return new MainActivityPresenter();
    }

    @Singleton
    @Provides
    DatabaseAdapter provideDatabaseAdapterMA(Context context, String codeLanguage) {
        mDataBaseAdapter = new DatabaseAdapter(context, codeLanguage);
        return mDataBaseAdapter;
    }

    @Singleton
    @Provides
    List<Country> provideCountries() {
        return mDataBaseAdapter.getCountries();
    }
}
