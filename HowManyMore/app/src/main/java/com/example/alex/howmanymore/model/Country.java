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
    private int id;
    private String nameISO;
    private String nameENG;
    private String nameRUS;
    private int flag = -1;
    private float sexesLife;
    private float sexesFemale;
    private float sexesMale;

    public Country(int id, String nameISO, String nameENG, String nameRUS, int flag, float sexesLife,
                   float sexesFemale, float sexesMale) {
        this.id = id;
        this.nameISO = nameISO;
        this.nameENG = nameENG;
        this.nameRUS = nameRUS;
        this.flag = flag;
        this.sexesLife = sexesLife;
        this.sexesFemale = sexesFemale;
        this.sexesMale = sexesMale;
    }

    public Country(String nameISO, String nameENG, String nameRUS, int flag) {
        this.nameISO = nameISO;
        this.nameENG = nameENG;
        this.nameRUS = nameRUS;
        this.flag = flag;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public String getNameISO() {
        return nameISO;
    }

    public String getNameENG() {
        return nameENG;
    }

    public String getNameRUS() {
        return nameRUS;
    }

    public int getFlag() {
        return flag;
    }

    public float getSexesLife() {
        return sexesLife;
    }

    public float getSexesFemale() {
        return sexesFemale;
    }

    public float getSexesMale() {
        return sexesMale;
    }

    public void loadFlagByCode(Context context) {
        if (this.flag != -1)
            return;

        try {
            this.flag = context.getResources()
                    .getIdentifier("flag_" + this.nameISO.toLowerCase(Locale.ENGLISH), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            this.flag = -1;
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
        this.id = in.readInt();

        String[] strings = new String[3];
        in.readStringArray(strings);
        this.nameISO = strings[0];
        this.nameENG = strings[1];
        this.nameRUS = strings[3];

        this.flag = in.readInt();

        float[] floats = new float[3];
        in.readFloatArray(floats);
        this.sexesLife = floats[0];
        this.sexesFemale = floats[1];
        this.sexesMale = floats[3];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringArray(new String[] {nameISO, nameENG, nameRUS});
        dest.writeInt(flag);
        dest.writeFloatArray(new float[]{sexesLife, sexesFemale, sexesMale});
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
