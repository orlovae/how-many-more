package com.example.alex.howmanymore.model;

/**
 * Created by alex on 07.01.18.
 */

class ModelCalendar {
    private int mYear, mMonth, mDay;

    ModelCalendar(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
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
}
