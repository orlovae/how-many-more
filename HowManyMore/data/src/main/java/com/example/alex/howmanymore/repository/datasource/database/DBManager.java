package com.example.alex.howmanymore.repository.datasource.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.howmanymore.entity.CountryEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by alex on 05.07.17.
 */

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private List<CountryEntity> countryEntityList;

    private final String TAG = this.getClass().getSimpleName();

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbHelper.udateDataBase();
        createCountryEntityList();
    }

    public List<CountryEntity> getCountryEntityList() {
        return countryEntityList;
    }

    private void createCountryEntityList() {
        countryEntityList = new ArrayList<CountryEntity>();

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, null, null,
                null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    countryEntityList.add(getCountryEntityFromCursor(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            countryEntityList = Collections.unmodifiableList(countryEntityList);
        }
    }

    public CountryEntity getCountryEntity(int countryEntityId) {
        CountryEntity countryEntity = null;

        String selection = Contract.LiveCountry.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {"%" + countryEntityId + "%"};

        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, null, selection,
                selectionArgs, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()){
                do {
                   countryEntity = getCountryEntityFromCursor(cursor);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return countryEntity;
    }

    private CountryEntity getCountryEntityFromCursor(Cursor cursor) {
        int countryEntityIDColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_ID);
        int nameISOColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_ISO);
        int nameENGColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_ENG);
        int nameRUSColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_COUNTRY_NAME_RUS);
        int sexesLifeColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_SEXES_LIFE);
        int femaleLifeColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_FEMALE_LIFE);
        int maleLifeColIndex = cursor.getColumnIndex(Contract.LiveCountry.COLUMN_MALE_LIFE);

        int countryEntityIDFromCursor = cursor.getInt(countryEntityIDColIndex);

        String nameISOFromCursor = cursor.getString(nameISOColIndex);
        String nameENGFromCursor = cursor.getString(nameENGColIndex);
        String nameRUSFromCursor = cursor.getString(nameRUSColIndex);

        int flag =  loadFlagByCode(context, nameISOFromCursor);

        float sexesLifeFromCursor = cursor.getFloat(sexesLifeColIndex);
        float femaleLifeFromCursor = cursor.getFloat(femaleLifeColIndex);
        float maleLifeFromCursor = cursor.getFloat(maleLifeColIndex);

        return new CountryEntity(
                countryEntityIDFromCursor,
                nameISOFromCursor,
                nameENGFromCursor,
                nameRUSFromCursor,
                flag,
                sexesLifeFromCursor,
                femaleLifeFromCursor,
                maleLifeFromCursor
        );
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

//    private String getColumns(String codeLanguage) {
//        String сolumns = null;
//        switch (codeLanguage) {
//            case EN:
//                сolumns = Contract.LiveCountry.COLUMN_COUNTRY_NAME_ENG;
//                break;
//            case RU:
//                сolumns = Contract.LiveCountry.COLUMN_COUNTRY_NAME_RUS;
//                break;
//        }
//        return сolumns;
//    }
//
//    public List<String> getNameCountry(String codeLanguage) {
//        List<String> nameCountry = new ArrayList<String>();
//
//        switch (codeLanguage) {
//            case EN:
//                for (Country item: countryEntityList
//                     ) {
//                    nameCountry.add(item.getNameENG());
//                }
//                break;
//            case RU:
//                for (Country item: countryEntityList
//                        ) {
//                    nameCountry.add(item.getNameRUS());
//                }
//                break;
//        }
//        return nameCountry;
//    }



//    public float getLifeExpectancy(String countryNameISO, String sex) {
//        float lifeExpectancy = 0;
//
//        String[] columns = new String[1];
//        switch (sex) {
//            case Keys.SEXES:
//                columns[0] = Contract.LiveCountry.COLUMN_SEXES_LIFE;
//                break;
//            case Keys.FEMALE:
//                columns[0] = Contract.LiveCountry.COLUMN_FEMALE_LIFE;
//                break;
//            case Keys.MALE:
//                columns[0] = Contract.LiveCountry.COLUMN_MALE_LIFE;
//                break;
//        }
//
//        String selection = Contract.LiveCountry.COLUMN_COUNTRY_NAME_ISO + " = ?";
//        String[] selectionArgs = {countryNameISO};
//
//
//        Cursor cursor = query(Contract.LiveCountry.TABLE_NAME, columns, selection, selectionArgs,
//                null, null, null);
//
//        try {
//            if (cursor != null && cursor.moveToFirst()){
//                do {
//                    lifeExpectancy = cursor.getFloat(0);
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return lifeExpectancy;
//    }

//    public void insertInTableUserRequests (User user){
//        ContentValues cv = new ContentValues();
//        float yearLifeExpectancy = user.getLifeExpectancy();
//        long birthday = user.getBirthday();
//        int nameCountry = user.getCountryFlag();
//        String sex = user.getSex();
//
//        cv.put(Contract.UserRequests.COLUMN_YEAR_LIFE_EXPECTANCY, yearLifeExpectancy);
//        cv.put(Contract.UserRequests.COLUMN_BIRTHDAY, birthday);
//        cv.put(Contract.UserRequests.COLUMN_COUNTRY, nameCountry);
//        cv.put(Contract.UserRequests.COLUMN_SEX, sex);
//
//        sqLiteDatabase.insert(Contract.UserRequests.TABLE_NAME, null, cv);
//    }

    private Cursor query(String table_name, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having, String sortOrder) {
        Cursor cursor = null;
        try {
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query(table_name, columns, selection,
                    selectionArgs, null, null, sortOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
