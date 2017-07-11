package com.example.alex.howmanymore.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.data.Contract;
import com.example.alex.howmanymore.data.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 05.07.17.
 */

public class DatabaseAdapter implements Constants {

    private DBHelper mDBHelper;
    private SQLiteDatabase mDataBase;

    public DatabaseAdapter (Context context) {
        mDBHelper = new DBHelper(context.getApplicationContext());
        mDBHelper.udateDataBase();
    }

    public List<String> getListCountry(String codeLanguage) {
        List<String> listCountry = new ArrayList<>();

        String[] columns = new String[1];
        String columnSortOrder = null;

        switch (codeLanguage) {
            case Constants.EN:
                columns[0] = Contract.LiveCountry.COLUMN_COUNTRY_EN;
                columnSortOrder = Contract.LiveCountry.COLUMN_COUNTRY_EN;
                break;
            case Constants.RU:
                columns[0] = Contract.LiveCountry.COLUMN_COUNTRY_RU;
                columnSortOrder = Contract.LiveCountry.COLUMN_COUNTRY_RU;
                break;
        }

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, columns, null, null,
                columnSortOrder + " ASC");

        try {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    String country = cursor.getString(0);
                    /**Так как в cursor всего 1 столбец, то хардкорим "0" **/
                    listCountry.add(country);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return listCountry;
    }

    private String getCountryAnotherLanguage(String country, String codeLanguage) {
        String countryAnotherLanguage = null;

        String[] columns = {Contract.LiveCountry.COLUMN_COUNTRY_EN,
                Contract.LiveCountry.COLUMN_COUNTRY_RU};
        String selection = null;
        String[] selectionArgs = {country};

        switch (codeLanguage) {
            case Constants.EN:
                selection = Contract.LiveCountry.COLUMN_COUNTRY_EN + " = ?";
                break;
            case Constants.RU:
                selection = Contract.LiveCountry.COLUMN_COUNTRY_RU + " = ?";
                break;
        }

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, columns, selection, selectionArgs,
                null);

        try {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    int countryColIndex = 0;

                    switch (codeLanguage) {
                        case Constants.EN:
                            countryColIndex = cursor.getColumnIndex(Contract.LiveCountry.
                                    COLUMN_COUNTRY_EN);
                            break;
                        case Constants.RU:
                            countryColIndex = cursor.getColumnIndex(Contract.LiveCountry.
                                    COLUMN_COUNTRY_RU);
                            break;
                    }

                    countryAnotherLanguage = cursor.getString(countryColIndex);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return countryAnotherLanguage;
    }

    private float getYearLifeExpectancy (String country, String sex) {
        float yearLifeExpectancy = 0;

        String[] columns = new String[1];
        String selection = Contract.LiveCountry.COLUMN_COUNTRY_EN
                         + " = ? OR "
                         + Contract.LiveCountry.COLUMN_COUNTRY_RU
                         + " = ?";
        String[] selectionArgs = {country};

        switch (sex) {
            case Constants.SEXES:
                columns[0] = Contract.LiveCountry.COLUMN_SEXES_LIFE;
                break;
            case Constants.FEMALE:
                columns[0] = Contract.LiveCountry.COLUMN_FEMALE_LIFE;
                break;
            case Constants.MALE:
                columns[0] = Contract.LiveCountry.COLUMN_MALE_LIFE;
                break;
        }

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, columns, selection, selectionArgs,
                null);

        try {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    yearLifeExpectancy = cursor.getFloat(0);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return yearLifeExpectancy;
    }

    private Cursor query(String table_name, String[] columns, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            mDataBase = mDBHelper.getReadableDatabase();
            cursor = mDataBase.query(table_name, columns, selection,
                    selectionArgs, null, null, sortOrder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDataBase.close();
        }
        return cursor;
    }
}
