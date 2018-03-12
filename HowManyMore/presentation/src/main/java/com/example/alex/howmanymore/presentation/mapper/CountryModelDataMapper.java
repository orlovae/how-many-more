package com.example.alex.howmanymore.presentation.mapper;

import com.example.alex.howmanymore.domain.Country;
import com.example.alex.howmanymore.presentation.model.CountryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by alex on 05.03.18.
 */

public class CountryModelDataMapper {
    public CountryModelDataMapper() {
    }

    public CountryModel transform(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final CountryModel countryModel = new CountryModel(country.getCountryId());
        countryModel.setNameENG(country.getNameENG());
        countryModel.setNameRUS(country.getNameRUS());
        countryModel.setFlag(country.getFlag());
        countryModel.setSexesLife(country.getSexesLife());
        countryModel.setSexesFemale(country.getSexesFemale());
        countryModel.setSexesMale(country.getSexesMale());

        return countryModel;
    }

    public Collection<CountryModel> transform(Collection<Country> countryCollection) {
        Collection<CountryModel> countryModelCollection;
        if (countryCollection != null && !countryCollection.isEmpty()) {
            countryModelCollection = new ArrayList<>();
            for (Country country : countryCollection) {
                countryModelCollection.add(transform(country));
            }
        } else {
            countryModelCollection = Collections.emptyList();
        }

        return countryModelCollection;
    }
}
