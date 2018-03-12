package com.example.alex.howmanymore.presentation.model.text;

import com.example.alex.howmanymore.presentation.model.Period;
import com.example.alex.howmanymore.presentation.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.presentation.constants.Keys.MILLIS_IN_ONE_YEAR;

/**
 * Created by alex on 15.01.18.
 */

public abstract class Text {
    protected Calendar mToDay = Calendar.getInstance();
    protected Calendar mBirthday = Calendar.getInstance();
    protected Calendar mLifeExpectancy;

    private String mResourcesText;
    User mUser;

    Text(String string, User user) {
        mResourcesText = string;
        mUser = user;
    }

    abstract float getPercent();

    abstract Period getPeriod();


    private StringToOnDraw getStringToOnDraw() {
        initCalendars();

        Period period = getPeriod();

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

    public String getText() {
        return concatenatesStrings(getPercent(), mResourcesText, getStringToOnDraw());
    }

    private String concatenatesStrings(float percent, String initialString,
                                       StringToOnDraw stringToOnDraw) {
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
}
