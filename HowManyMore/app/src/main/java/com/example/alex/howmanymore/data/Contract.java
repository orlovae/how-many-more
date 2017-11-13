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
        public static final String COLUMN_COUNTRY_NAME_ISO = "nameISO";
        public static final String COLUMN_COUNTRY_NAME_ENG = "nameENG";
        public static final String COLUMN_COUNTRY_NAME_RUS = "nameRUS";
        public static final String COLUMN_SEXES_LIFE = "sexesLife";
        public static final String COLUMN_FEMALE_LIFE = "femaleLife";
        public static final String COLUMN_MALE_LIFE = "maleLife";
    }

    public static final class UserRequests implements BaseColumns {
        public static final String TABLE_NAME = "UserRequests";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_YEAR_LIFE_EXPECTANCY = "yearLifeExpectancy";
        public static final String COLUMN_BIRTHDAY = "birthday";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_SEX = "sex";

        public static final String SQL_CREATE_USER_REQUESTS_TABLE =
                String.format("CREATE TABLE %s ("
                + "%s integer primary key autoincrement,"
                + "%s integer,"
                + "%s integer,"
                + "%s text,"
                + "%s text);",
                        TABLE_NAME, COLUMN_ID, COLUMN_YEAR_LIFE_EXPECTANCY, COLUMN_BIRTHDAY,
                        COLUMN_COUNTRY, COLUMN_SEX);
    }
}
