package com.example.alex.howmanymore.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.presentation.constants.Keys.DAY_IN_ONE_LEAP_YEAR;
import static com.example.alex.howmanymore.presentation.constants.Keys.DAY_IN_ONE_YEAR;
import static com.example.alex.howmanymore.presentation.constants.Keys.FEMALE;
import static com.example.alex.howmanymore.presentation.constants.Keys.MALE;
import static com.example.alex.howmanymore.presentation.constants.Keys.SEXES;

/**
 * Created by alex on 09.11.17.
 */

//TODO класс и хранит модель и обрабатывает её. Нужно разделить в два класса.

public class User implements Parcelable {
    private float lifeExpectancy, lifeLived, percentLived;
    private long birthday;
    private int mCountryFlag;
    private String sex;

    private CountryModel countryModel;

    public User() {
    }

    private void setLifeExpectancy() {
        if (countryModel != null) {
            switch (sex) {
                case SEXES:
                    lifeExpectancy = countryModel.getSexesLife();
                    break;
                case FEMALE:
                    lifeExpectancy = countryModel.getSexesFemale();
                case MALE:
                    lifeExpectancy = countryModel.getSexesMale();
            }
        } else {
            throw new NullPointerException("countryModel is null");
        }

    }

    public float getLifeExpectancy() {
        setLifeExpectancy();
        return lifeExpectancy;
    }

    public float getLifeLived() {
        setLifeLived();
        return lifeLived; }

    private void setLifeLived() {
        Calendar toDay = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(this.birthday);

        Period period = new Period(birthday, toDay);

        Calendar calendar = new GregorianCalendar(period.getYears(), period.getMonths(), period.getDays());

        int yearRemained = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayInYear = getDayInYear((GregorianCalendar) toDay);

        lifeLived = (float) yearRemained + (float) dayOfYear / dayInYear;
    }

    public float getPercentLived() {
        setPercentLived();
        return percentLived;
    }

    private void setPercentLived() {
        percentLived = 0;
        try {
            percentLived = (lifeLived / lifeExpectancy)
                    * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getDayInYear(GregorianCalendar toDay) {
        return toDay.isLeapYear(toDay.get(Calendar.YEAR)) ? DAY_IN_ONE_LEAP_YEAR : DAY_IN_ONE_YEAR;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getCountryFlag() {
        return mCountryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        mCountryFlag = countryFlag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getLifeExpectancy(), user.getLifeExpectancy())
                .append(getLifeLived(), user.getLifeLived())
                .append(getPercentLived(), user.getPercentLived())
                .append(getBirthday(), user.getBirthday())
                .append(getCountryFlag(), user.getCountryFlag())
                .append(getSex(), user.getSex())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLifeExpectancy())
                .append(getLifeLived())
                .append(getPercentLived())
                .append(getBirthday())
                .append(getCountryFlag())
                .append(getSex())
                .toHashCode();
    }

    public final static Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }
    };

    private User(Parcel in) {
        lifeExpectancy = in.readFloat();
        lifeLived = in.readFloat();
        percentLived = in.readFloat();
        birthday = in.readLong();
        mCountryFlag = in.readInt();
        sex = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(lifeExpectancy);
        dest.writeFloat(lifeLived);
        dest.writeFloat(percentLived);
        dest.writeLong(birthday);
        dest.writeInt(mCountryFlag);
        dest.writeString(sex);
    }
}
