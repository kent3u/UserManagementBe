package com.example.demo.adapter.web.client;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
class ClientDto {
    UUID id;
    UUID userId;
    String firstName;
    String lastName;
    String username;
    String email;
    String address;
    String countryIso;
}
