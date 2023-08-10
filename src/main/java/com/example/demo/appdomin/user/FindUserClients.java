package com.example.demo.appdomin.user;

import com.example.demo.appdomin.client.Client;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface FindUserClients {

    Set<UserClient> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        User.Id userId;
    }

    @Value
    @Builder
    class UserClient {
        Client.Id id;
        User.Id userId;
        String firstName;
        String lastName;
        String username;
    }
}
