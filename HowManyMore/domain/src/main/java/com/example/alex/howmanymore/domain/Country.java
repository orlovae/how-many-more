package com.example.alex.howmanymore.domain;

/**
 * Created by alex on 21.02.18.
 */

public class Country {

    private final int countryId;

    public Country(int countryId) {
        this.countryId = countryId;
    }

    private String NameENG;
    private String NameRUS;
    private int flag;
    private float sexesLife;
    private float sexesFemale;
    private float sexesMale;

    public int getCountryId() {
        return countryId;
    }

    public String getNameENG() {
        return NameENG;
    }

    public void setNameENG(String nameENG) {
        NameENG = nameENG;
    }

    public String getNameRUS() {
        return NameRUS;
    }

    public void setNameRUS(String nameRUS) {
        NameRUS = nameRUS;
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


//    public final static Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
//
//        public Country createFromParcel(Parcel in) {
//            return new Country(in);
//        }
//
//        public Country[] newArray(int size) {
//            return (new Country[size]);
//        }
//    };
//
//    private Country(Parcel in) {
//        mId = in.readInt();
//
//        String[] strings = new String[3];
//        in.readStringArray(strings);
//        mNameISO = strings[0];
//        mNameENG = strings[1];
//        mNameRUS = strings[3];
//
//        mFlag = in.readInt();
//
//        float[] floats = new float[3];
//        in.readFloatArray(floats);
//        mSexesLife = floats[0];
//        mSexesFemale = floats[1];
//        mSexesMale = floats[3];
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(mId);
//        dest.writeStringArray(new String[] {mNameISO, mNameENG, mNameRUS});
//        dest.writeInt(mFlag);
//        dest.writeFloatArray(new float[]{mSexesLife, mSexesFemale, mSexesMale});
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
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

