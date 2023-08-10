package com.example.demo.adapter.web.client;

import com.example.demo.adapter.web.NotFoundException;
import com.example.demo.appdomin.client.ChangeClient;
import com.example.demo.appdomin.client.Client;
import com.example.demo.appdomin.client.CreateClient;
import com.example.demo.appdomin.client.GetClientById;
import com.example.demo.appdomin.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
class ClientController {

    private final GetClientById getClientById;
    private final CreateClient createClient;
    private final ChangeClient changeClient;

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable UUID id) {
        return getClientById.execute(GetClientById.Request.of(Client.Id.of(id)))
                .map(client -> ClientDto.builder()
                .id(client.getId().getValue())
                .userId(client.getUserId().getValue())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .username(client.getUsername())
                .email(client.getEmail())
                .address(client.getAddress())
                .countryIso(client.getCountryIso())
                .build())
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void createClient(@RequestBody ClientCreationDto clientCreationDto) {
        UUID userId = UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        createClient.execute(CreateClient.Request.builder()
                .userId(User.Id.of(userId))
                .firstName(clientCreationDto.getFirstName())
                .lastName(clientCreationDto.getLastName())
                .username(clientCreationDto.getUsername())
                .email(clientCreationDto.getEmail())
                .address(clientCreationDto.getAddress())
                .countryIso(clientCreationDto.getCountryIso())
                .build());
    }

    @PutMapping("/{id}")
    public void changeClient(@PathVariable UUID id,
                             @RequestBody ClientChangeDto clientChangeDto) {
        changeClient.execute(ChangeClient.Request.builder()
                .id(Client.Id.of(id))
                .firstName(clientChangeDto.getFirstName())
                .lastName(clientChangeDto.getLastName())
                .username(clientChangeDto.getUsername())
                .email(clientChangeDto.getEmail())
                .address(clientChangeDto.getAddress())
                .countryIso(clientChangeDto.getCountryIso())
                .build());
    }
}
