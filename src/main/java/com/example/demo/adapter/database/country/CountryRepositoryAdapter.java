package com.example.demo.adapter.database.country;

import com.example.demo.adapter.web.country.FindAllCountries;
import com.example.demo.appdomin.country.Country;
import com.example.demo.appdomin.country.IsCountryIsoExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class CountryRepositoryAdapter implements IsCountryIsoExists, FindAllCountries {

    private final CountryEntityRepository countryEntityRepository;

    @Override
    public boolean execute(Request request) {
        return countryEntityRepository.existsById(request.getCountryIso());
    }

    @Override
    public Set<Country> execute() {
        return countryEntityRepository.findAll()
                .stream()
                .map(countryEntity -> Country.of(countryEntity.getIso(), countryEntity.getName()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
