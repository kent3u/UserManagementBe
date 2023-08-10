package com.example.demo.adapter.web.country;

import lombok.Value;

@Value(staticConstructor = "of")
class CountryDto {
    String iso;
    String name;
}
