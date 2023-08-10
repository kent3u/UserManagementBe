package com.example.demo.adapter.web.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ClientCreationDto {

    String firstName;
    String lastName;
    String username;
    String email;
    String address;
    String countryIso;
}
