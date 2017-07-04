package com.example.alex.howmanymore.model;

import java.util.Calendar;

/**
 * Created by alex on 03.07.17.
 */

public class Model {

    private float yearLifeExpectancy;

    private Calendar birthday;

    public Model(float yearLifeExpectancy, Calendar birthday) {
        this.yearLifeExpectancy = yearLifeExpectancy;
        this.birthday = birthday;
    }

    public float getYearLifeExpectancy(String country) {
        return yearLifeExpectancy;
    }

    public void setYearLifeExpectancy(float yearLifeExpectancy, String country) {
        this.yearLifeExpectancy = yearLifeExpectancy;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}
