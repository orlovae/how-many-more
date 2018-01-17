package com.example.alex.howmanymore.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.alex.howmanymore.constants.Keys.DAY_IN_ONE_LEAP_YEAR;
import static com.example.alex.howmanymore.constants.Keys.DAY_IN_ONE_YEAR;

/**
 * Created by alex on 09.11.17.
 */

//TODO класс и хранит модель и обрабатывает её. Нужно разделить в два класса.

public class User implements Parcelable {
    private float mLifeExpectancy, mLifeLived, mPercentLived;
    private long mBirthday;
    private int mCountryFlag;
    private String mSex;

    public User() {
    }

    public float getLifeExpectancy() {
        return mLifeExpectancy;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        mLifeExpectancy = lifeExpectancy;
    }

    public float getLifeLived() {
        setLifeLived();
        return mLifeLived; }

    private void setLifeLived() {
        Calendar toDay = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTimeInMillis(mBirthday);

        Period period = new Period(birthday, toDay);

        Calendar calendar = new GregorianCalendar(period.getYears(), period.getMonths(), period.getDays());

        int yearRemained = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayInYear = getDayInYear((GregorianCalendar) toDay);

        mLifeLived = (float) yearRemained + (float) dayOfYear / dayInYear;
    }

    public float getPercentLived() {
        setPercentLived();
        return mPercentLived;
    }

    private void setPercentLived() {
        mPercentLived = 0;
        try {
            mPercentLived = (mLifeLived / mLifeExpectancy)
                    * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getDayInYear(GregorianCalendar toDay) {
        return toDay.isLeapYear(toDay.get(Calendar.YEAR)) ? DAY_IN_ONE_LEAP_YEAR : DAY_IN_ONE_YEAR;
    }

    public long getBirthday() {
        return mBirthday;
    }

    public void setBirthday(long birthday) {
        mBirthday = birthday;
    }

    public int getCountryFlag() {
        return mCountryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        mCountryFlag = countryFlag;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
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
        mLifeExpectancy = in.readFloat();
        mLifeLived = in.readFloat();
        mPercentLived = in.readFloat();
        mBirthday = in.readLong();
        mCountryFlag = in.readInt();
        mSex = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(mLifeExpectancy);
        dest.writeFloat(mLifeLived);
        dest.writeFloat(mPercentLived);
        dest.writeLong(mBirthday);
        dest.writeInt(mCountryFlag);
        dest.writeString(mSex);
    }
}
