package com.example.alex.howmanymore.presentation.model;

import java.util.Calendar;

/**
 * Created by alex on 07.01.18.
 */

public class Period {
    private int mYear, mMonth, mDay;

    public Period(Calendar first, Calendar last) {
        setPeriod(first, last);
    }

    public int getYears() {
        return mYear;
    }

    public int getMonths() {
        return mMonth;
    }

    public int getDays() {
        return mDay;
    }

    private void setPeriod(Calendar first, Calendar last) {
        mYear = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
        mMonth = last.get(Calendar.MONTH) - first.get(Calendar.MONTH);
        mDay = last.get(Calendar.DAY_OF_MONTH) - first.get(Calendar.DAY_OF_MONTH);

        if (mDay < 0) {
            mMonth = mMonth - 1;

            if (mMonth < 0) {
                mMonth = mMonth + 12;
                mYear = mYear - 1;
            }

            mDay = first.getActualMaximum(Calendar.DAY_OF_MONTH)
                    - first.get(Calendar.DAY_OF_MONTH)
                    + last.get(Calendar.DAY_OF_MONTH);
        }

        if (mMonth < 0) {
            mMonth = mMonth + 12;
            mYear = mYear - 1;
        }
    }
}
