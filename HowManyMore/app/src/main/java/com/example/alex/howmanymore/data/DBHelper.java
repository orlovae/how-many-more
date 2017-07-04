package com.example.alex.howmanymore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alex on 03.07.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HowManyMore.db";
    public static final int TABLE_VERSION = 1;

    private final String SQL_CREATE_LIVECOUNTRY_TABLE = "CREATE TABLE "
            + Contract.LiveCountry.TABLE_NAME + " ("
            + Contract.LiveCountry.COLUMN_ID + " integer primary key autoincrement,"
            + Contract.LiveCountry.COLUMN_COUNTRY_RU + " text,"
            + Contract.LiveCountry.COLUMN_COUNTRY_EN + " text,"
            + Contract.LiveCountry.COLUMN_SEXES_LIFE + " integer,"
            + Contract.LiveCountry.COLUMN_FEMALE_LIFE + " integer,"
            + Contract.LiveCountry.COLUMN_MALE_LIFE + " integer);";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LIVECOUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.LiveCountry.TABLE_NAME);
        onCreate(db);
    }
}
