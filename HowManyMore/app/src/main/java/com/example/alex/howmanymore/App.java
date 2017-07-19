package com.example.alex.howmanymore;

import android.app.Application;
import android.content.Context;

import com.example.alex.howmanymore.adapter.DatabaseAdapter;

/**
 * Created by alex on 06.07.17.
 */

public class App extends Application {
    private DatabaseAdapter mDBAdapter;

    private static Context context;

    public static Context getAppContext() {
        return App.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        init();

    }

    private DatabaseAdapter init() {
        if (mDBAdapter == null){
            mDBAdapter = new DatabaseAdapter(this);
        }
        return mDBAdapter;
    }

}
