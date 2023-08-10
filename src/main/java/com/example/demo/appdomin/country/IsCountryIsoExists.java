package com.example.demo.appdomin.country;

import lombok.Value;

@FunctionalInterface
public interface IsCountryIsoExists {

    boolean execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        String countryIso;
    }
}
