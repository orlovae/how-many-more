package com.example.alex.howmanymore.presentation.dagger;

import android.content.Context;

import com.example.alex.howmanymore.domain.interactor.Interactor;
import com.example.alex.howmanymore.domain.ResourceManager;
import com.example.alex.howmanymore.domain.repository.CountryRepository;
import com.example.alex.howmanymore.entity.mapper.CountryEntityDataMapper;
import com.example.alex.howmanymore.repository.CountryDataRepository;
import com.example.alex.howmanymore.repository.datasource.CountryDataStoreFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 12.03.18.
 */

@Module
public class MainActivityPresenterModule {
    private final CountryRepository countryRepository;

    public MainActivityPresenterModule(Context context) {
        CountryDataStoreFactory countryDataStoreFactory = new CountryDataStoreFactory(context);
        countryRepository = new CountryDataRepository(countryDataStoreFactory,
                new CountryEntityDataMapper());
    }

    @Provides
    Interactor provideIteractor() {
        return new Interactor(countryRepository);
    }
}
