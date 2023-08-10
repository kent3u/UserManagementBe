package com.example.demo.appdomin.country;

import lombok.Value;

@Value(staticConstructor = "of")
public class Country {
    String iso;
    String name;
}
