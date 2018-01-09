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

        Calendar mLifeExpectancy = getCalendarLifeExpectancy(mUser.getLifeExpectancy());

        ModelCalendar lived = getPeriod(mBirthday, mToDay);

        mYearLivedPercent = (getLifeLived(mUser.getBirthday())
                / mUser.getLifeExpectancy()) * 100;

        mLived = new StringToOnDraw(
                lived.getYears(),
                lived.getMonths(),
                lived.getDays());

        ModelCalendar remained = getPeriod(mLifeExpectancy, mBirthday);

        mRemained = new StringToOnDraw(
                remained.getYears(),
                remained.getMonths(),
                remained.getDays());
    }

    private Calendar getCalendarLifeExpectancy(float lifeExpectancy) {
        Calendar calLifeExpectancy = new GregorianCalendar();

        calLifeExpectancy.setTimeInMillis(mToDay.getTimeInMillis() - (long) (lifeExpectancy * 365.2425f * 24 *60 *60 *1000) );

        return calLifeExpectancy;
    }

    private ModelCalendar getPeriod(Calendar first, Calendar last) {
        int year = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
        int month = last.get(Calendar.MONTH) - first.get(Calendar.MONTH);
        int day = last.get(Calendar.DAY_OF_MONTH) - first.get(Calendar.DAY_OF_MONTH);

        if (day < 0) {
            month = month - 1;

            if (month < 0) {
                month = month + 12;
                year = year - 1;
            }

            day = first.getActualMaximum(Calendar.DAY_OF_MONTH)
                    - first.get(Calendar.DAY_OF_MONTH) + last.get(Calendar.DAY_OF_MONTH);
        }

        if (month < 0) {
            month = month + 12;
            year = year - 1;
        }

        return new ModelCalendar(year, month, day);
    }

    public float getLifeLived(long birthday) {
        mBirthday.setTimeInMillis(birthday);

        ModelCalendar model = getPeriod(mBirthday, mToDay);

        Calendar calendar = new GregorianCalendar(model.getYears(), model.getMonths(), model.getDays());

        int yearRemained = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayInYear = getDayInYear((GregorianCalendar) mToDay);

        return (float) yearRemained + (float) dayOfYear / dayInYear;
    }

    private int getDayInYear(GregorianCalendar toDay) {
        return toDay.isLeapYear(toDay.get(Calendar.YEAR)) ? 366 : 365;
    }
}