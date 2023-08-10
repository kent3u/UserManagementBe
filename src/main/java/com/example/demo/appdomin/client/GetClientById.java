package com.example.demo.appdomin.client;

import lombok.Value;

import java.util.Optional;

@FunctionalInterface
public interface GetClientById {

    Optional<Client> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Client.Id id;
    }
}
