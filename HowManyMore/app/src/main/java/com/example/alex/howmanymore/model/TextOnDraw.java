package com.example.alex.howmanymore.model;

import android.content.Context;

import com.example.alex.howmanymore.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.constants.Keys.BLACK;
import static com.example.alex.howmanymore.constants.Keys.DAY_IN_ONE_LEAP_YEAR;
import static com.example.alex.howmanymore.constants.Keys.DAY_IN_ONE_YEAR;
import static com.example.alex.howmanymore.constants.Keys.MILLIS_IN_ONE_YEAR;
import static com.example.alex.howmanymore.constants.Keys.WHITE;

/**
 * Created by alex on 27.11.17.
 */

public class TextOnDraw {
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    private User mUser;

    private Calendar mToDay = Calendar.getInstance();
    private Calendar mBirthday = Calendar.getInstance();
    private Calendar mLifeExpectancy;

    public TextOnDraw(Context context, User user) {
        mContext = context;
        mUser = user;
    }

    public String getText(String key) {//TODO уточнить зависимость классов
        initCalendars();

        StringToOnDraw lived = getStringToOnDraw(mBirthday, mToDay);
        StringToOnDraw remained = getStringToOnDraw(mLifeExpectancy, mBirthday);


        String text = null;

        switch (key) {
            case WHITE:
                text = concatenatesStrings(getYearLivedPercent(),
                        mContext.getResources().getString(R.string.draw_lived),
                        lived);
                break;
            case BLACK:
                text = concatenatesStrings(100 - getYearLivedPercent(),
                        mContext.getResources().getString(R.string.draw_remained),
                        remained);
                break;
        }
        return text;
    }

    private float getYearLivedPercent() {
        return (getLifeLived(mUser.getBirthday())
                / mUser.getLifeExpectancy()) * 100;
    }

    private String concatenatesStrings(float percent, String initialString, StringToOnDraw stringToOnDraw) {
        String lastString = "("
                + String.format("%(.2f", percent)
                + "%)";

        if (stringToOnDraw.getStringYear() != null) {
            initialString = initialString + stringToOnDraw.getStringYear();
        }

        if (stringToOnDraw.getStringMount() != null) {
            initialString = initialString + stringToOnDraw.getStringMount();
        }

        if (stringToOnDraw.getStringDay() != null) {
            initialString = initialString + stringToOnDraw.getStringDay() + lastString;
        } else {
            initialString = initialString + lastString;
        }
        return initialString;
    }

    private StringToOnDraw getStringToOnDraw(Calendar initial, Calendar last) {
        Period period = new Period(initial, last);

        return new StringToOnDraw(
                period.getYears(),
                period.getMonths(),
                period.getDays());
    }

    private void initCalendars() {
        mBirthday.setTimeInMillis(mUser.getBirthday());
        mLifeExpectancy = getCalendarLifeExpectancy(mUser.getLifeExpectancy());
    }

    private Calendar getCalendarLifeExpectancy(float lifeExpectancy) {
        Calendar calLifeExpectancy = new GregorianCalendar();

        calLifeExpectancy.setTimeInMillis(
                mToDay.getTimeInMillis() - (long) (lifeExpectancy * MILLIS_IN_ONE_YEAR)
        );

        return calLifeExpectancy;
    }

    public float getLifeLived(long birthday) {//TODO уточнить зависимость классов
        mBirthday.setTimeInMillis(birthday);

        Period period = new Period(mBirthday, mToDay);

        Calendar calendar = new GregorianCalendar(period.getYears(), period.getMonths(), period.getDays());

        int yearRemained = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayInYear = getDayInYear((GregorianCalendar) mToDay);

        return (float) yearRemained + (float) dayOfYear / dayInYear;
    }

    private int getDayInYear(GregorianCalendar toDay) {
        return toDay.isLeapYear(toDay.get(Calendar.YEAR)) ? DAY_IN_ONE_LEAP_YEAR : DAY_IN_ONE_YEAR;
    }
}