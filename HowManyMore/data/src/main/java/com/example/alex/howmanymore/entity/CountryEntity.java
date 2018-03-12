package com.example.alex.howmanymore.entity;

/**
 * Created by alex on 22.02.18.
 */

public class CountryEntity {
    private int countryEntityId;
    private String nameISO;
    private String nameENG;
    private String nameRUS;
    private int flag = -1;
    private float sexesLife;
    private float sexesFemale;
    private float sexesMale;

    public CountryEntity() {
    }

    public CountryEntity(int countryEntityId, String nameISO, String nameENG, String nameRUS, int flag, float sexesLife, float sexesFemale, float sexesMale) {
        this.countryEntityId = countryEntityId;
        this.nameISO = nameISO;
        this.nameENG = nameENG;
        this.nameRUS = nameRUS;
        this.flag = flag;
        this.sexesLife = sexesLife;
        this.sexesFemale = sexesFemale;
        this.sexesMale = sexesMale;
    }

    public int getCountryEntityId() {
        return countryEntityId;
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
}
