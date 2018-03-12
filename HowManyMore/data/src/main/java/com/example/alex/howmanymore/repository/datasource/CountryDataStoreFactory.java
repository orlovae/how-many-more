package com.example.alex.howmanymore.repository.datasource;

import android.content.Context;

import com.example.alex.howmanymore.repository.datasource.database.DBManager;

/**
 * Created by alex on 22.02.18.
 */

public class CountryDataStoreFactory {
    private final Context context;

    public CountryDataStoreFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    public CountryDataStore createDBCountryDataStore() {
        DBManager dbManager = new DBManager(context);
        return new DBCountryDataStore(dbManager);
    }

    public CountryDataStore createCloudCountryDataStore() {
        /*empty*/
        return new CloudCountryDataStore();
    }
}
