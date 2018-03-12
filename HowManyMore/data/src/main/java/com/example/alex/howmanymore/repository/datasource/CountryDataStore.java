package com.example.alex.howmanymore.repository.datasource;

import com.example.alex.howmanymore.entity.CountryEntity;

import java.util.List;

/**
 * Created by alex on 22.02.18.
 */

public interface CountryDataStore {
    List<CountryEntity> countryEntityList();
    CountryEntity countryEntityDetails(final int countryId);
}
