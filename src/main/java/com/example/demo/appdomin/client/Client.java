package com.example.demo.appdomin.client;

import com.example.demo.appdomin.user.User;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Client {
    Id id;
    User.Id userId;
    String firstName;
    String lastName;
    String username;
    String email;
    String address;
    String countryIso;

    @Value(staticConstructor = "of")
    public static class Id {
        UUID value;
    }
}
