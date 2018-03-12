package com.example.alex.howmanymore.repository.datasource;

import com.example.alex.howmanymore.entity.CountryEntity;
import com.example.alex.howmanymore.repository.datasource.database.DBManager;

import java.util.List;

/**
 * Created by alex on 22.02.18.
 */

public class DBCountryDataStore implements CountryDataStore {
    private DBManager dbManager;

    public DBCountryDataStore(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<CountryEntity> countryEntityList() {
        return dbManager.getCountryEntityList();
    }

    @Override
    public CountryEntity countryEntityDetails(final int countryId) {
        return dbManager.getCountryEntity(countryId);
    }
}
