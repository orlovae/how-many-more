package com.example.alex.howmanymore.domain.interactor;

import com.example.alex.howmanymore.domain.Country;
import com.example.alex.howmanymore.domain.repository.CountryRepository;

import java.util.List;

/**
 * Created by alex on 28.02.18.
 */

public class Interactor {

    private final CountryRepository countryRepository;

    public Interactor(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountryList() {
        return countryRepository.countries();
    }
}
