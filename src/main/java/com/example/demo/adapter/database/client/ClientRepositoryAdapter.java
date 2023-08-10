package com.example.demo.adapter.database.client;

import com.example.demo.appdomin.client.Client;
import com.example.demo.appdomin.client.GetClientById;
import com.example.demo.appdomin.client.SaveClient;
import com.example.demo.appdomin.user.FindUserClients;
import com.example.demo.appdomin.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ClientRepositoryAdapter implements FindUserClients, SaveClient, GetClientById {

    private final ClientEntityRepository clientEntityRepository;

    @Override
    public Set<UserClient> execute(FindUserClients.Request request) {
        return clientEntityRepository.findByUserId(request.getUserId().getValue()).stream()
                .map(clientEntity -> UserClient.builder()
                        .id(Client.Id.of(clientEntity.getId()))
                        .userId(User.Id.of(clientEntity.getUserId()))
                        .firstName(clientEntity.getFirstName())
                        .lastName(clientEntity.getLastName())
                        .username(clientEntity.getUsername())
                        .build())
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void execute(SaveClient.Request request) {
        clientEntityRepository.save(toEntity(request.getClient()));
    }

    @Override
    public Optional<Client> execute(GetClientById.Request request) {
        return clientEntityRepository.findById(request.getId().getValue())
                .map(clientEntity -> Client.builder()
                        .id(Client.Id.of(clientEntity.getId()))
                        .userId(User.Id.of(clientEntity.getUserId()))
                        .firstName(clientEntity.getFirstName())
                        .lastName(clientEntity.getLastName())
                        .username(clientEntity.getUsername())
                        .email(clientEntity.getEmail())
                        .address(clientEntity.getAddress())
                        .countryIso(clientEntity.getCountryIso())
                        .build());
    }

    private ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId().getValue())
                .userId(client.getUserId().getValue())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .username(client.getUsername())
                .email(client.getEmail())
                .address(client.getAddress())
                .countryIso(client.getCountryIso())
                .build();
    }
}
