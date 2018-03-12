package com.example.alex.howmanymore.entity.mapper;

import com.example.alex.howmanymore.domain.Country;
import com.example.alex.howmanymore.entity.CountryEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 22.02.18.
 */

public class CountryEntityDataMapper {

    public CountryEntityDataMapper() {
    }

    public Country transform(CountryEntity countryEntity) {
        Country country = null;
        if (countryEntity != null) {
            country = new Country(countryEntity.getCountryEntityId());
            country.setNameENG(countryEntity.getNameENG());
            country.setNameRUS(countryEntity.getNameRUS());
            country.setFlag(countryEntity.getFlag());
            country.setSexesLife(countryEntity.getSexesLife());
            country.setSexesFemale(countryEntity.getSexesFemale());
            country.setSexesMale(countryEntity.getSexesMale());
        }
        return country;
    }

    public List<Country> transform(Collection<CountryEntity> countryEntityCollection) {
        final List<Country> countryList = new ArrayList<>();
        for (CountryEntity countryEntity : countryEntityCollection) {
            final Country country = transform(countryEntity);
            if (country != null) {
                countryList.add(country);
            }
        }
        return countryList;
    }
}
