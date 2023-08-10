package com.example.demo.appdomin.client;

import lombok.Value;

@FunctionalInterface
public interface SaveClient {

    void execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Client client;
    }
}
