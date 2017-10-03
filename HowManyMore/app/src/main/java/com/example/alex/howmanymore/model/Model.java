package com.example.alex.howmanymore.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Comparator;

/**
 * Created by alex on 03.07.17.
 */

public class Model implements Parcelable {
    private int id;

    private float yearLifeExpectancy;

    private long birthday;

    private String country, sex;

    public Model(float yearLifeExpectancy, long birthday, String country, String sex) {
        this.yearLifeExpectancy = yearLifeExpectancy;
        this.birthday = birthday;
        this.country = country;
        this.sex = sex;
    }

    public Model(int id, float yearLifeExpectancy, long birthday, String country, String sex) {
        this.id = id;
        this.yearLifeExpectancy = yearLifeExpectancy;
        this.birthday = birthday;
        this.country = country;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(yearLifeExpectancy)
                .append(birthday)
                .append(country)
                .append(sex)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Model) == false) {
            return false;
        }
        Model rhs = ((Model) other);
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(yearLifeExpectancy, rhs.yearLifeExpectancy)
                .append(birthday, rhs.birthday)
                .append(country, rhs.country)
                .append(sex, rhs.sex)
                .isEquals();
    }

    public static final Comparator<Model> COMPARE_BY_ID = new Comparator<Model>() {
        @Override
        public int compare(Model lhs, Model rhs) {
            return lhs.getId() - rhs.getId();
        }
    };

    public final static Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {

        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        public Model[] newArray(int size) {
            return (new Model[size]);
        }
    };

    private Model(Parcel in) {
        this.id = in.readInt();
        this.yearLifeExpectancy = in.readFloat();
        this.birthday = in.readLong();
        String[] data = new String[2];
        in.readStringArray(data);
        this.country = data[0];
        this.sex = data[1];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(yearLifeExpectancy);
        dest.writeLong(birthday);
        dest.writeStringArray(new String[] {country, sex});
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
