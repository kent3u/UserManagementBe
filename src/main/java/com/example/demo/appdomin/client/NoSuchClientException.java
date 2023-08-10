package com.example.demo.appdomin.client;

public class NoSuchClientException extends RuntimeException {

    public NoSuchClientException() {
        super("Client not found");
    }
}
