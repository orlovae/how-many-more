package com.example.alex.howmanymore.model;

import android.content.Context;
import android.util.Log;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.constants.Keys;

import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.constants.Keys.BLACK;
import static com.example.alex.howmanymore.constants.Keys.WHITE;
import static java.lang.Math.abs;

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
        float yearLifeExpectancy = mUser.getLifeExpectancy();
        Calendar toDay = GregorianCalendar.getInstance();
        Calendar calBirthday = GregorianCalendar.getInstance();
        calBirthday.setTimeInMillis(mUser.getBirthday());

        LocalDate lDToDay = new LocalDate(
                toDay.get(Calendar.YEAR),
                toDay.get(Calendar.MONTH) + 1,
                toDay.get(Calendar.DAY_OF_MONTH));

        LocalDate lDBirthday = new LocalDate(
                calBirthday.get(Calendar.YEAR),
                calBirthday.get(Calendar.MONTH) + 1,
                calBirthday.get(Calendar.DAY_OF_MONTH));


        Period period = new Period(lDBirthday, lDToDay);

        mYearLivedPercent = ((
                (float) period.getYears() + (float) calBirthday.get(Calendar.DAY_OF_YEAR) / getDayOfYear())
                / yearLifeExpectancy)
                * 100;

        mLived = new StringToOnDraw(period.getYears(), period.getMonths(), period.getDays());

        float yearRemained = toDay.get(Calendar.YEAR) - yearLifeExpectancy;

        float mountRemained = toDay.get(Calendar.MONTH) + 1 - ((yearLifeExpectancy - (int) yearLifeExpectancy) * 12);
        if (mountRemained < 0) {
            mountRemained = 12 - mountRemained;
            yearRemained = yearRemained - 1;
        }
        float dayRemained = toDay.get(Calendar.DAY_OF_MONTH) - ((mountRemained - (int) mountRemained) * Keys.ONE_MOUNT);
        if (dayRemained < 0) {
            dayRemained = Keys.ONE_MOUNT - dayRemained;
            mountRemained = mountRemained - 1;
            if (mountRemained < 0) {
                mountRemained = 12 - mountRemained;
                yearRemained = yearRemained - 1;
            }
        }
        if (dayRemained > 30) {
            dayRemained = 30;
        }

        Log.d(TAG, "yearRemained = " + yearRemained);
        Log.d(TAG, "mountRemained = " + mountRemained);
        Log.d(TAG, "dayRemained = " + dayRemained);

        LocalDate lDLifeExpectancy = new LocalDate(
                (int) yearRemained,
                (int) mountRemained,
                (int) dayRemained);

        Period periodRemained = new Period(lDLifeExpectancy, lDBirthday);

        Log.d(TAG, "periodRemained.getYears() = " +  periodRemained.getYears());
        Log.d(TAG, "periodRemained.getMonths() = " + periodRemained.getMonths());
        Log.d(TAG, "periodRemained.getDays() = " + periodRemained.getDays());

        mRemained = new StringToOnDraw(
                periodRemained.getYears(),
                periodRemained.getMonths(),
                periodRemained.getDays());
    }

    public float getLifeLived() {
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(mUser.getBirthday());

        Calendar toDay = GregorianCalendar.getInstance();
        int yearLived = toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        return yearLived + (float) birthday.get(Calendar.DAY_OF_YEAR) / getDayOfYear();
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