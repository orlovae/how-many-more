package com.example.alex.howmanymore.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Locale;

/**
 * Created by alex on 03.07.17.
 */

public class Country implements Parcelable {
    private int mId;
    private String mNameISO;
    private String mNameENG;
    private String mNameRUS;
    private int mFlag = -1;
    private float mSexesLife;
    private float mSexesFemale;
    private float mSexesMale;

    public Country(int id, String nameISO, String nameENG, String nameRUS, int flag, float sexesLife,
                   float sexesFemale, float sexesMale) {
        mId = id;
        mNameISO = nameISO;
        mNameENG = nameENG;
        mNameRUS = nameRUS;
        mFlag = flag;
        mSexesLife = sexesLife;
        mSexesFemale = sexesFemale;
        mSexesMale = sexesMale;
    }

    public Country(String nameISO, String nameENG, String nameRUS, int flag) {
        mNameISO = nameISO;
        mNameENG = nameENG;
        mNameRUS = nameRUS;
        mFlag = flag;
    }

    public Country() {
    }

    public int getId() {
        return mId;
    }

    public String getNameISO() {
        return mNameISO;
    }

    public String getNameENG() {
        return mNameENG;
    }

    public String getNameRUS() {
        return mNameRUS;
    }

    public int getFlag() {
        return mFlag;
    }

    public float getSexesLife() {
        return mSexesLife;
    }

    public float getSexesFemale() {
        return mSexesFemale;
    }

    public float getSexesMale() {
        return mSexesMale;
    }

    public void loadFlagByCode(Context context) {
        if (mFlag != -1)
            return;

        try {
            mFlag = context.getResources()
                    .getIdentifier("flag_" + mNameISO.toLowerCase(Locale.ENGLISH), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            mFlag = -1;
        }
    }

    public final static Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {

        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return (new Country[size]);
        }
    };

    private Country(Parcel in) {
        mId = in.readInt();

        String[] strings = new String[3];
        in.readStringArray(strings);
        mNameISO = strings[0];
        mNameENG = strings[1];
        mNameRUS = strings[3];

        mFlag = in.readInt();

        float[] floats = new float[3];
        in.readFloatArray(floats);
        mSexesLife = floats[0];
        mSexesFemale = floats[1];
        mSexesMale = floats[3];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeStringArray(new String[] {mNameISO, mNameENG, mNameRUS});
        dest.writeInt(mFlag);
        dest.writeFloatArray(new float[]{mSexesLife, mSexesFemale, mSexesMale});
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        return new EqualsBuilder()
                .append(getId(), country.getId())
                .append(getFlag(), country.getFlag())
                .append(getSexesLife(), country.getSexesLife())
                .append(getSexesFemale(), country.getSexesFemale())
                .append(getSexesMale(), country.getSexesMale())
                .append(getNameISO(), country.getNameISO())
                .append(getNameENG(), country.getNameENG())
                .append(getNameRUS(), country.getNameRUS())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getNameISO())
                .append(getNameENG())
                .append(getNameRUS())
                .append(getFlag())
                .append(getSexesLife())
                .append(getSexesFemale())
                .append(getSexesMale())
                .toHashCode();
    }
}
