package com.example.demo.appdomin.client;

import com.example.demo.appdomin.ValidationException;
import com.example.demo.appdomin.user.User;
import com.example.demo.appdomin.validation.ValidateClientCommon;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateClient {

    private final SaveClient saveClient;
    private final ValidateClientCommon validateClientCommon;

    public void execute(Request request) {
        validateRequest(request);

        UUID newId = UUID.randomUUID();
        Client newClient = toDomainClient(newId, request);

        saveClient.execute(SaveClient.Request.of(newClient));
    }

    private void validateRequest(Request request) {
        var errorCodes = validateClientCommon.execute(ValidateClientCommon.Request.builder()
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .address(request.getAddress())
                .countryIso(request.getCountryIso())
                .build());

        if (!errorCodes.isEmpty()) {
            throw ValidationException.of(errorCodes);
        }
    }

    private Client toDomainClient(UUID newId, Request request) {
        return Client.builder()
                .id(Client.Id.of(newId))
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .address(request.getAddress())
                .countryIso(request.getCountryIso())
                .build();
    }

    @Value
    @Builder
    public static class Request {
        User.Id userId;
        String firstName;
        String lastName;
        String username;
        String email;
        String address;
        String countryIso;
    }
}
