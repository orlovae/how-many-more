package com.example.alex.howmanymore.repository;

import com.example.alex.howmanymore.domain.Country;
import com.example.alex.howmanymore.domain.repository.CountryRepository;
import com.example.alex.howmanymore.entity.mapper.CountryEntityDataMapper;
import com.example.alex.howmanymore.repository.datasource.CountryDataStore;
import com.example.alex.howmanymore.repository.datasource.CountryDataStoreFactory;

import java.util.List;

/**
 * Created by alex on 22.02.18.
 */

public class CountryDataRepository implements CountryRepository {

    private final CountryDataStoreFactory countryDataStoreFactory;
    private final CountryEntityDataMapper countryEntityDataMapper;

    public CountryDataRepository(CountryDataStoreFactory countryDataStoreFactory, CountryEntityDataMapper countryEntityDataMapper) {
        this.countryDataStoreFactory = countryDataStoreFactory;
        this.countryEntityDataMapper = countryEntityDataMapper;
    }

    @Override
    public List<Country> countries() {
        final CountryDataStore countryDataStore = this.countryDataStoreFactory.createDBCountryDataStore();
        return countryEntityDataMapper.transform(countryDataStore.countryEntityList());
    }

    @Override
    public Country country(int countryId) {
        final CountryDataStore countryDataStore = this.countryDataStoreFactory.createDBCountryDataStore();
        return countryEntityDataMapper.transform(countryDataStore.countryEntityDetails(countryId));
    }
}
