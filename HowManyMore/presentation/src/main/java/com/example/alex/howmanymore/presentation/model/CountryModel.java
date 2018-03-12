package com.example.alex.howmanymore.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 21.02.18.
 */

public class CountryModel implements Parcelable {

    private final int countryModelId;

    public CountryModel(int countryModelId) {
        this.countryModelId = countryModelId;
    }

    private String nameENG;
    private String nameRUS;
    private int flag;
    private float sexesLife;
    private float sexesFemale;
    private float sexesMale;

    public int getCountryModelId() {
        return countryModelId;
    }

    public String getNameENG() {
        return nameENG;
    }

    public void setNameENG(String nameENG) {
        this.nameENG = nameENG;
    }

    public String getNameRUS() {
        return nameRUS;
    }

    public void setNameRUS(String nameRUS) {
        this.nameRUS = nameRUS;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public float getSexesLife() {
        return sexesLife;
    }

    public void setSexesLife(float sexesLife) {
        this.sexesLife = sexesLife;
    }

    public float getSexesFemale() {
        return sexesFemale;
    }

    public void setSexesFemale(float sexesFemale) {
        this.sexesFemale = sexesFemale;
    }

    public float getSexesMale() {
        return sexesMale;
    }

    public void setSexesMale(float sexesMale) {
        this.sexesMale = sexesMale;
    }

    public final static Parcelable.Creator<CountryModel> CREATOR = new Parcelable.Creator<CountryModel>() {

        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        public CountryModel[] newArray(int size) {
            return (new CountryModel[size]);
        }
    };

    private CountryModel(Parcel in) {
        countryModelId = in.readInt();

        String[] strings = new String[2];
        in.readStringArray(strings);
        nameENG = strings[0];
        nameRUS = strings[1];

        flag = in.readInt();

        float[] floats = new float[3];
        in.readFloatArray(floats);
        sexesLife = floats[0];
        sexesFemale = floats[1];
        sexesMale = floats[3];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(countryModelId);
        dest.writeStringArray(new String[] {nameENG, nameRUS});
        dest.writeInt(flag);
        dest.writeFloatArray(new float[]{sexesLife, sexesFemale, sexesMale});
    }

    @Override
    public int describeContents() {
        return 0;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (!(o instanceof Country)) return false;
//
//        Country country = (Country) o;
//
//        return new EqualsBuilder()
//                .append(getId(), country.getId())
//                .append(getFlag(), country.getFlag())
//                .append(getSexesLife(), country.getSexesLife())
//                .append(getSexesFemale(), country.getSexesFemale())
//                .append(getSexesMale(), country.getSexesMale())
//                .append(getNameISO(), country.getNameISO())
//                .append(getNameENG(), country.getNameENG())
//                .append(getNameRUS(), country.getNameRUS())
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//                .append(getId())
//                .append(getNameISO())
//                .append(getNameENG())
//                .append(getNameRUS())
//                .append(getFlag())
//                .append(getSexesLife())
//                .append(getSexesFemale())
//                .append(getSexesMale())
//                .toHashCode();
//    }
}

