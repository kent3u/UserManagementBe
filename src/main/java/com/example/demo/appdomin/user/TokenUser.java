package com.example.demo.appdomin.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TokenUser extends User {

    private UUID userId;

    public TokenUser(String username, String password, UUID userId) {
        super(username, password, Set.of());
        this.userId = userId;
    }
}
