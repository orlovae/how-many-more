package com.example.alex.howmanymore.model;

import android.content.Context;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.constants.Keys;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by alex on 27.11.17.
 */

public class TextOnDraw {
    private Context mContext;
    private User mUser;

    private float mYearLivedPercent;

    private StringToOnDraw mLived, mRemained;


    public TextOnDraw(Context context, User user) {
        mContext = context;
        mUser = user;
    }

    public String getText(String key) {
        prepare();

        String text = null;

        switch (key) {
            case "white":
                text = getString(mYearLivedPercent,
                        mContext.getResources().getString(R.string.draw_lived),
                        mLived);
                break;
            case "black":
                text = getString(100 - mYearLivedPercent,
                        mContext.getResources().getString(R.string.draw_remained),
                        mRemained);
        }
        return text;
    }

    private String getString(float percent, String initString, StringToOnDraw stringToOnDraw) {
        String string = initString + stringToOnDraw.getStringYear();

        if (stringToOnDraw.getStringMount() != null) {
            string = string + stringToOnDraw.getStringMount();
        }

        if (stringToOnDraw.getStringDay() != null) {
            string = string
                    + stringToOnDraw.getStringDay()
                    + ". ("
                    + String.format("%(.2f", percent)
                    + "%)";
        } else {
            string = string
                    + ". ("
                    + String.format("%(.2f", percent)
                    + "%)";
        }
        return string;
    }


    private void prepare() {
        float yearLifeExpectancy = mUser.getYearLifeExpectancy();
        Calendar toDay = GregorianCalendar.getInstance();

        long birthday = mUser.getBirthday();
        long lived = toDay.getTimeInMillis() - birthday;
        int daysLived = (int) (lived / (Keys.ONE_DAY_IN_MILLISECONDS));
        float yearLived = daysLived / Keys.ONE_YEAR;
        mYearLivedPercent = (yearLived / yearLifeExpectancy) * 100;

        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(birthday);

        mLived = new StringToOnDraw(
                yearLived,
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        float yearRemained = yearLifeExpectancy - yearLived;
        float mountRemained = (yearRemained - (int) yearRemained) * 12;

        mRemained = new StringToOnDraw(
                yearRemained,
                mountRemained,
                (mountRemained - (int) mountRemained) * Keys.ONE_MOUNT);
    }
}