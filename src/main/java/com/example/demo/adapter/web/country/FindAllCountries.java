package com.example.demo.adapter.web.country;

import com.example.demo.appdomin.country.Country;

import java.util.Set;

@FunctionalInterface
public interface FindAllCountries {

    Set<Country> execute();
}
