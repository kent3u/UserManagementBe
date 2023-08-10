package com.example.demo.appdomin.client;

import com.example.demo.appdomin.ValidationException;
import com.example.demo.appdomin.validation.ValidateClientCommon;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeClient {

    private final GetClientById getClientById;
    private final SaveClient saveClient;
    private final ValidateClientCommon validateClientCommon;

    public void execute(Request request) {
        Client existingClient = getClientById.execute(GetClientById.Request.of(request.getId()))
                .orElseThrow(NoSuchClientException::new);
        validateRequest(request, existingClient);

        Client newClient = updateExistingClient(existingClient, request);

        saveClient.execute(SaveClient.Request.of(newClient));
    }

    private void validateRequest(Request request, Client existingClient) {
        var errorCodes = validateClientCommon.execute(ValidateClientCommon.Request.builder()
                .userId(existingClient.getUserId())
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

    private Client updateExistingClient(Client existingClient, Request request) {
        return existingClient.toBuilder()
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
        Client.Id id;
        String firstName;
        String lastName;
        String username;
        String email;
        String address;
        String countryIso;
    }
}
