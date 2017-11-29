package com.example.alex.howmanymore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.alex.howmanymore.constants.Keys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.alex.howmanymore.constants.Keys.DB_NAME;
import static com.example.alex.howmanymore.constants.Keys.DB_VERSION;
import static com.example.alex.howmanymore.data.Contract.UserRequests.SQL_CREATE_USER_REQUESTS_TABLE;

/**
 * Created by alex on 03.07.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + Keys.DB_PATH_SDK_INT_MORE_17;
        } else {
            DB_PATH = Keys.DB_PATH_SDK_INT_LESS_17
                    + context.getPackageName()
                    + Keys.DB_PATH_SDK_INT_MORE_17;
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
                throw new Error(Keys.ERROR_COPYING_DATA_BASE);
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
        db.execSQL(SQL_CREATE_USER_REQUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            mNeedUpdate = true;
        }
    }
}
