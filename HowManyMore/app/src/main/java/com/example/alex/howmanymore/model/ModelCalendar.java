package com.example.alex.howmanymore.model;

/**
 * Created by alex on 07.01.18.
 */

public class ModelCalendar {
    private int mYear, mMount, mDay;

    public ModelCalendar(int year, int mount, int day) {
        mYear = year;
        mMount = mount;
        mDay = day;
    }

    public int getYear() {
        return mYear;
    }

    public int getMount() {
        return mMount;
    }

    public int getDay() {
        return mDay;
    }
}
