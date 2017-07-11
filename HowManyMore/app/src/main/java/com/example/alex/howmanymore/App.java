package com.example.alex.howmanymore;

import android.app.Application;

import com.example.alex.howmanymore.adapter.DatabaseAdapter;

/**
 * Created by alex on 06.07.17.
 */

public class App extends Application {
    private DatabaseAdapter mDBAdapter;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    private DatabaseAdapter init() {
        if (mDBAdapter == null){
            mDBAdapter = new DatabaseAdapter(this);
        }
        return mDBAdapter;
    }

}
