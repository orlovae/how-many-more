package com.example.alex.howmanymore.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by alex on 09.11.17.
 */

public class User implements Parcelable {
    private float yearLifeExpectancy;
    private long birthday;
    private String nameCountry;
    private String sex;

    public User(float yearLifeExpectancy, long birthday, String nameCountry, String sex) {
        this.yearLifeExpectancy = yearLifeExpectancy;
        this.birthday = birthday;
        this.nameCountry = nameCountry;
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

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getYearLifeExpectancy(), user.getYearLifeExpectancy())
                .append(getBirthday(), user.getBirthday())
                .append(getNameCountry(), user.getNameCountry())
                .append(getSex(), user.getSex())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getYearLifeExpectancy())
                .append(getBirthday())
                .append(getNameCountry())
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
        this.yearLifeExpectancy = in.readFloat();
        this.birthday = in.readLong();

        String[] strings = new String[2];
        in.readStringArray(strings);
        this.nameCountry = strings[0];
        this.sex = strings[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(yearLifeExpectancy);
        dest.writeLong(birthday);
        dest.writeStringArray(new String[] {nameCountry, sex});
    }
}
