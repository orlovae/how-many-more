package com.example.alex.howmanymore.data;

import android.provider.BaseColumns;

/**
 * Created by alex on 03.07.17.
 */

public final class Contract {

    public Contract() {
    }

    public static final class LiveCountry implements BaseColumns {
        public static final String TABLE_NAME = "LifeCountry";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_COUNTRY_EN = "countryEN";
        public static final String COLUMN_COUNTRY_RU = "countryRU";
        public static final String COLUMN_SEXES_LIFE = "sexesLife";
        public static final String COLUMN_FEMALE_LIFE = "femaleLife";
        public static final String COLUMN_MALE_LIFE = "maleLife";
    }
}
