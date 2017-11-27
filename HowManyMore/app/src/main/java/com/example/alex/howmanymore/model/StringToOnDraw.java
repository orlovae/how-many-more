package com.example.alex.howmanymore.model;

import com.example.alex.howmanymore.constants.DayRus;
import com.example.alex.howmanymore.constants.MountRus;
import com.example.alex.howmanymore.constants.YearsRus;

/**
 * Created by alex on 27.11.17.
 */

class StringToOnDraw {
    private String mStringYear, mStringMount, mStringDay;

    StringToOnDraw(float year, float mount, float day) {
        setStringYear(year);
        setStringMount(mount);
        setStringDay(day);
    }

    String getStringYear() {
        return mStringYear;
    }

    String getStringMount() {
        return mStringMount;
    }

    String getStringDay() {
        return mStringDay;
    }

    private void setStringYear(float year) {
        mStringYear = " - "
                + (int) year
                + " "
                + YearsRus.getNameYearsRus((int) year)
                + ", ";
    }

    private void setStringMount(float mount) {
        mStringMount = null;

        if (mount > 0) {
            mStringMount = (int) mount
                    + " "
                    + MountRus.getNameMountsRus((int) mount)
                    + ", ";
        }
    }

    private void setStringDay(float day) {
        mStringDay = null;

        if (day > 0) {
            mStringDay = (int) day
                    + " "
                    + DayRus.getNameDaysRus((int) day);
        }
    }
}
