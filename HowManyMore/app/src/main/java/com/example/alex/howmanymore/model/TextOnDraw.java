package com.example.alex.howmanymore.model;

import android.content.Context;
import android.util.Log;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.constants.Keys;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.constants.Keys.BLACK;
import static com.example.alex.howmanymore.constants.Keys.WHITE;

/**
 * Created by alex on 27.11.17.
 */

public class TextOnDraw {
    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private User mUser;

    private float mYearLivedPercent;

    private int mYearLived;

    private StringToOnDraw mLived, mRemained;


    public TextOnDraw(Context context, User user) {
        mContext = context;
        mUser = user;
    }

    public String getText(String key) {
        prepare();
        String text = null;

        switch (key) {
            case WHITE:
                text = getString(mYearLivedPercent,
                        mContext.getResources().getString(R.string.draw_lived),
                        mLived);
                break;
            case BLACK:
                text = getString(100 - mYearLivedPercent,
                        mContext.getResources().getString(R.string.draw_remained),
                        mRemained);
        }
        return text;
    }

    private String getString(float percent, String initString, StringToOnDraw stringToOnDraw) {
        String string = initString + stringToOnDraw.getStringYear();
        String endString = ". ("
                + String.format("%(.2f", percent)
                + "%)";

        if (stringToOnDraw.getStringMount() != null) {
            string = string + stringToOnDraw.getStringMount();
        }

        if (stringToOnDraw.getStringDay() != null) {
            string = string + stringToOnDraw.getStringDay() + endString;
        } else {
            string = string + endString;
        }
        return string;
    }


    private void prepare() {
        float yearLifeExpectancy = mUser.getLifeExpectancy();
        Calendar toDay = GregorianCalendar.getInstance();
        Calendar birthday = GregorianCalendar.getInstance();
        birthday.setTimeInMillis(mUser.getBirthday());

        int dayLived = toDay.get(Calendar.DATE) - birthday.get(Calendar.DATE);
        int mountLived = toDay.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
        mYearLived = toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);


        if (dayLived < 0) {
            dayLived = toDay.get(Calendar.DATE)
                    + birthday.getActualMaximum(Calendar.DAY_OF_MONTH)
                    - birthday.get(Calendar.DATE);

            mYearLived = toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) - 1;
            if (mountLived - 1 < 0) {
                mountLived = 11;
                mYearLived = mYearLived - 1;
            } else {
                mountLived = mountLived -1;
            }
        } else {
            mountLived = toDay.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
        }

        mYearLivedPercent = (mYearLived / yearLifeExpectancy) * 100;

        mLived = new StringToOnDraw(mYearLived, mountLived, dayLived);

        float yearRemained = yearLifeExpectancy - mYearLived;
        float mountRemained = (yearRemained - (int) yearRemained) * 12;

        mRemained = new StringToOnDraw(
                yearRemained,
                mountRemained,
                (mountRemained - (int) mountRemained) * Keys.ONE_MOUNT);
    }

    public float getLifeLived() {
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(mUser.getBirthday());

        return mYearLived + (float) birthday.get(Calendar.DAY_OF_YEAR) / getDayOfYear();
    }

    private int getDayOfYear() {
        GregorianCalendar toDay = (GregorianCalendar) GregorianCalendar.getInstance();

        if (toDay.isLeapYear(toDay.get(Calendar.YEAR))){
            return 366;
        } else {
            return 365;
        }
    }
}