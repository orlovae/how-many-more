package com.example.alex.howmanymore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alex on 03.07.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HowManyMore.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    private final String SQL_CREATE_LIVECOUNTRY_TABLE = "CREATE TABLE "
            + Contract.LiveCountry.TABLE_NAME + " ("
            + Contract.LiveCountry.COLUMN_ID + " integer primary key autoincrement,"
            + Contract.LiveCountry.COLUMN_COUNTRY_RU + " text,"
            + Contract.LiveCountry.COLUMN_COUNTRY_EN + " text,"
            + Contract.LiveCountry.COLUMN_SEXES_LIFE + " integer,"
            + Contract.LiveCountry.COLUMN_FEMALE_LIFE + " integer,"
            + Contract.LiveCountry.COLUMN_MALE_LIFE + " integer);";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data" + context.getPackageName() + "/databases/";
        }

        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void udateDataBase(){
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
            }

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase(){
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();

            try {
                copyDBFile();
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }

        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLiteException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LIVECOUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            mNeedUpdate = true;
        }
    }
}
