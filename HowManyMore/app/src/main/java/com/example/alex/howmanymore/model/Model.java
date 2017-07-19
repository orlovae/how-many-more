package com.example.alex.howmanymore.model;

/**
 * Created by alex on 03.07.17.
 */

public class Model {
    private float yearLifeExpectancy;

    private long birthday;

    private String country, sex;

    public Model(float yearLifeExpectancy, long birthday, String country, String sex) {
        this.yearLifeExpectancy = yearLifeExpectancy;
        this.birthday = birthday;
        this.country = country;
        this.sex = sex;
    }

    public float getYearLifeExpectancy() {
        return yearLifeExpectancy;
    }

    public void setYearLifeExpectancy(float yearLifeExpectancy) {
        this.yearLifeExpectancy = yearLifeExpectancy;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
