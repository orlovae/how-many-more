package com.example.alex.howmanymore.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.data.Contract;
import com.example.alex.howmanymore.data.DBHelper;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.alex.howmanymore.Constants.EN;
import static com.example.alex.howmanymore.Constants.RU;

/**
 * Created by alex on 05.07.17.
 */

public class DatabaseAdapter {
    private Context mContext;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDataBase;
    private List<Country> mCountries;

    private final String TAG = this.getClass().getSimpleName();

    public DatabaseAdapter (Context context, String codeLanguage) {
        mContext = context;
        mDBHelper = new DBHelper(context);
        mDBHelper.udateDataBase();
        mCountries = getListCountry(codeLanguage);
    }

    public List<Country> getCountries() {
        return mCountries;
    }

    public List<Country> getListCountry(String сodeLanguage) {
        Log.d(TAG, "getListCountry: " + сodeLanguage);
        mCountries = new ArrayList<Country>();

        String sortOrder = getColumns(сodeLanguage) + " ASC";
        Log.d(TAG, "getListCountry: " + sortOrder);

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, null, null,
                null, null, null,
                sortOrder);

        try {
            if (cursor != null && cursor.moveToFirst()){
                int nameISOColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_ISO);
                int nameENGColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_ENG);
                int nameRUSColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_RUS);
                do {
                    String nameISOFromCursor = cursor.getString(nameISOColIndex);
                    String nameENGFromCursor = cursor.getString(nameENGColIndex);
                    String nameRUSFromCursor = cursor.getString(nameRUSColIndex);

                    int flag =  loadFlagByCode(mContext, nameISOFromCursor);

                    Country country = new Country(nameISOFromCursor, nameENGFromCursor,
                            nameRUSFromCursor, flag);

                    mCountries.add(country);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Log.d(TAG, "getListCountry.size = " + mCountries.size());
        return mCountries;
    }

    private String getColumns(String codeLanguage) {
        String сolumns = null;
        switch (codeLanguage) {
            case EN:
                сolumns = Contract.LiveCountry.COLUMN_COUNTRY_NAME_ENG;
                break;
            case RU:
                сolumns = Contract.LiveCountry.COLUMN_COUNTRY_NAME_RUS;
                break;
        }
        return сolumns;
    }

    public List<String> getNameCountry(String codeLanguage) {
        List<String> nameCountry = new ArrayList<String>();

        switch (codeLanguage) {
            case EN:
                for (Country item: mCountries
                     ) {
                    nameCountry.add(item.getNameENG());
                }
                break;
            case RU:
                for (Country item: mCountries
                        ) {
                    nameCountry.add(item.getNameRUS());
                }
                break;
        }
        return nameCountry;
    }

    private int loadFlagByCode(Context context, String nameISO) {
        try {
            return context.getResources()
                    .getIdentifier("flag_" + nameISO.toLowerCase(Locale.ENGLISH), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public float getYearLifeExpectancy (String countryNameISO, String sex) {
        float yearLifeExpectancy = 0;

        String[] columns = new String[1];
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

        String selection = Contract.LiveCountry.COLUMN_COUNTRY_NAME_ISO + " = ?";
        String[] selectionArgs = {countryNameISO};


        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);

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

    public void insertInTableUserRequests (User user){
        ContentValues cv = new ContentValues();
        float yearLifeExpectancy = user.getYearLifeExpectancy();
        long birthday = user.getBirthday();
        int nameCountry = user.getCountryFlag();
        String sex = user.getSex();

        cv.put(Contract.UserRequests.COLUMN_YEAR_LIFE_EXPECTANCY, yearLifeExpectancy);
        cv.put(Contract.UserRequests.COLUMN_BIRTHDAY, birthday);
        cv.put(Contract.UserRequests.COLUMN_COUNTRY, nameCountry);
        cv.put(Contract.UserRequests.COLUMN_SEX, sex);

        mDataBase.insert(Contract.UserRequests.TABLE_NAME, null, cv);
    }

    private Cursor query(String table_name, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having, String sortOrder) {
        Cursor cursor = null;
        try {
            mDataBase = mDBHelper.getReadableDatabase();
            cursor = mDataBase.query(table_name, columns, selection,
                    selectionArgs, null, null, sortOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
