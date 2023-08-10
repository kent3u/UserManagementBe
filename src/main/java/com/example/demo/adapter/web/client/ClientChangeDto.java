package com.example.demo.adapter.web.client;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ClientChangeDto {
    String firstName;
    String lastName;
    String username;
    String email;
    String address;
    String countryIso;
}
