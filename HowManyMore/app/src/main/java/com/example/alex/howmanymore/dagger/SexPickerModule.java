package com.example.alex.howmanymore.dagger;

import android.content.Context;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;

import java.util.TreeMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 19.11.17.
 */
@Module
public class SexPickerModule {

    @Singleton
    @Provides
    TreeMap<String, String> provideSexMap(Context context) {
        TreeMap<String, String> treeMapSex = new TreeMap<String, String>();
        treeMapSex.put(Constants.SEXES, context.getString(R.string.sexes));
        treeMapSex.put(Constants.FEMALE, context.getString(R.string.female));
        treeMapSex.put(Constants.MALE, context.getString(R.string.male));
        return treeMapSex;
    }
}
