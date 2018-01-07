package com.example.alex.howmanymore.model;

import android.content.Context;
import android.util.Log;

import com.example.alex.howmanymore.R;

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

    private StringToOnDraw mLived, mRemained;

    private Calendar mToDay = GregorianCalendar.getInstance();
    private Calendar mBirthday = GregorianCalendar.getInstance();
    private Calendar mLifeExpectancy;

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
        String string = initString;
        String endString = "("
                + String.format("%(.2f", percent)
                + "%)";

        if (stringToOnDraw.getStringYear() != null) {
            string = string + stringToOnDraw.getStringYear();
        }

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
        mBirthday.setTimeInMillis(mUser.getBirthday());

        mLifeExpectancy = getCalendarLifeExpectancy(mUser.getLifeExpectancy());

        Log.d(TAG, "prepare: LifeExpectancy = "
                + mLifeExpectancy.get(Calendar.YEAR) + ":"
                + mLifeExpectancy.get(Calendar.MONTH) + ":"
                + mLifeExpectancy.get(Calendar.DAY_OF_MONTH));

        ModelCalendar lived = getPeriod(
                mToDay.get(Calendar.YEAR) - mBirthday.get(Calendar.YEAR),
                mToDay.get(Calendar.MONTH) - mBirthday.get(Calendar.MONTH),
                mToDay.get(Calendar.DAY_OF_MONTH) - mBirthday.get(Calendar.DAY_OF_MONTH)
        );

        mYearLivedPercent = (getLifeLived(mUser.getLifeExpectancy(), mUser.getBirthday()) / mUser.getLifeExpectancy()) * 100;

        mLived = new StringToOnDraw(
                lived.getYear(),
                lived.getMount(),
                lived.getDay());

        ModelCalendar remained = getPeriod(
                mBirthday.get(Calendar.YEAR) - mLifeExpectancy.get(Calendar.YEAR),
                mBirthday.get(Calendar.MONTH) - mLifeExpectancy.get(Calendar.MONTH),
                mBirthday.get(Calendar.DAY_OF_MONTH) - mLifeExpectancy.get(Calendar.DAY_OF_MONTH)
        );

        mRemained = new StringToOnDraw(
                remained.getYear(),
                remained.getMount(),
                remained.getDay());
    }

    private Calendar getCalendarLifeExpectancy(float lifeExpectancy) {
        int year = mToDay.get(Calendar.YEAR) - (int) lifeExpectancy;
        float mount = ((int) lifeExpectancy - lifeExpectancy) * 12;

        Calendar calendar = new GregorianCalendar(year, (int) mount, 0);

        float day = ((int) mount - mount) * calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        ModelCalendar model = getPeriod(year, (int) mount, (int) day);

        return new GregorianCalendar(model.getYear(), model.getMount(), model.getDay());
    }

    private ModelCalendar getPeriod(int year, int mount, int day) {
        if (day < 0) {
            if (mount < 0) {
                mount = 11 + mount;
                year = year - 1;
            }
            int previousMount = mount - 1;
            if (previousMount < 0) {
                previousMount = 11;
            }

            Calendar previousCal = new GregorianCalendar(year, previousMount, 0);

            day = previousCal.getActualMaximum(Calendar.DAY_OF_MONTH) + day;
        }

        if (mount < 0) {
            mount = 11 + mount;
            year = year - 1;
        }

        return new ModelCalendar(year, mount, day);
    }

    public float getLifeLived(float lifeExpectancy, long birthday) {
        mBirthday.setTimeInMillis(birthday);

        int year = mToDay.get(Calendar.YEAR) - mBirthday.get(Calendar.YEAR);
        int mount = mToDay.get(Calendar.MONTH) - mBirthday.get(Calendar.MONTH);
        int day = mToDay.get(Calendar.DAY_OF_MONTH) - mBirthday.get(Calendar.DAY_OF_MONTH);

        ModelCalendar model = getPeriod(year, mount, day);

        if (model.getMount() - 1 < 0) {
            mount = 0;
        } else {
            mount = mount - 1;
        }

        Calendar calendar = new GregorianCalendar(model.getYear(), mount, model.getDay());

        int yearRemained = model.getYear();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayInYear = getDayOfYear((GregorianCalendar) mToDay);
        Log.d(TAG, "getLifeLived: yearRemained = " + yearRemained);
        Log.d(TAG, "getLifeLived: dayOfYear = " + dayOfYear);
        Log.d(TAG, "getLifeLived: dayInYear = " + dayInYear);

        float lifeLived = (float) yearRemained + (float) dayOfYear / dayInYear;

        Log.d(TAG, "getLifeLived: lifeLived = " + lifeLived);

        return lifeLived;
    }

    private int getDayOfYear(GregorianCalendar toDay) {
        return toDay.isLeapYear(toDay.get(Calendar.YEAR)) ? 366 : 365;
    }
}