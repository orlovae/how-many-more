package com.example.alex.howmanymore.domain.repository;

import com.example.alex.howmanymore.domain.Country;

import java.util.List;

/**
 * Created by alex on 21.02.18.
 */

public interface CountryRepository {

    List<Country> countries();
    Country country(final int countryId);
}
