package com.example.demo.adapter.web.user;

import com.example.demo.appdomin.user.FindUserClients.UserClient;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
class UserClientDto {
    UUID id;
    UUID userId;
    String firstName;
    String lastName;
    String username;

    public static UserClientDto of(UserClient userClient) {
        return UserClientDto.builder()
                .id(userClient.getId().getValue())
                .userId(userClient.getUserId().getValue())
                .firstName(userClient.getFirstName())
                .lastName(userClient.getLastName())
                .username(userClient.getUsername())
                .build();
    }
}
