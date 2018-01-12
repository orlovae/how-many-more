package com.example.alex.howmanymore.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by alex on 09.11.17.
 */

public class User implements Parcelable {
    private float mLifeExpectancy, mLifeLived;
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

    public float getLifeLived() { return mLifeLived; }

    public void setLifeLived(float lifeLived) {

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
                .append(getBirthday(), user.getBirthday())
                .append(getCountryFlag(), user.getCountryFlag())
                .append(getSex(), user.getSex())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLifeExpectancy())
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
        dest.writeLong(mBirthday);
        dest.writeInt(mCountryFlag);
        dest.writeString(mSex);
    }
}
