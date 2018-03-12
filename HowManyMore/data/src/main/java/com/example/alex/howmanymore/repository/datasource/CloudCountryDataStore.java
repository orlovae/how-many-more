package com.example.alex.howmanymore.repository.datasource;

import com.example.alex.howmanymore.entity.CountryEntity;

import java.util.List;

/**
 * Created by alex on 22.02.18.
 */

public class CloudCountryDataStore implements CountryDataStore {

    @Override
    public List<CountryEntity> countryEntityList() {
        return null;
    }

    @Override
    public CountryEntity countryEntityDetails(final int countryId) {
        return null;
    }
}
