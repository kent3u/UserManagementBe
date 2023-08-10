package com.example.demo.appdomin.user;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class User {
    Id id;
    String username;

    @Value(staticConstructor = "of")
    public static class Id {
        UUID value;
    }
}
