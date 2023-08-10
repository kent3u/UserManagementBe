package com.example.demo.adapter.web.country;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
class CountryController {

    private final FindAllCountries findAllCountries;

    @GetMapping
    public Set<CountryDto> findAll() {
        return findAllCountries.execute()
                .stream()
                .map(country -> CountryDto.of(country.getIso(), country.getName()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
