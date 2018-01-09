package com.example.alex.howmanymore.model;

/**
 * Created by alex on 07.01.18.
 */

public class ModelCalendar {
    private int mYear, mMonth, mDay;

    public ModelCalendar(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
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
}
