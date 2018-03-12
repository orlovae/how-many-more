package com.example.alex.howmanymore.presentation.dagger;

import android.content.Context;

import com.example.alex.howmanymore.presentation.constants.Keys;
import com.example.alex.howmanymore.presentation.R;

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
        treeMapSex.put(Keys.SEXES, context.getString(R.string.sexes));
        treeMapSex.put(Keys.FEMALE, context.getString(R.string.female));
        treeMapSex.put(Keys.MALE, context.getString(R.string.male));
        return treeMapSex;
    }
}
