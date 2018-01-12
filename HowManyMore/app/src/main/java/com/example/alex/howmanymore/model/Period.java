package com.example.alex.howmanymore.model;

import java.util.Calendar;

/**
 * Created by alex on 07.01.18.
 */

class Period {
    private int mYear, mMonth, mDay;

    Period(Calendar first, Calendar last) {
        setPeriod(first, last);
    }

    int getYears() {
        return mYear;
    }

    int getMonths() {
        return mMonth;
    }

    int getDays() {
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
