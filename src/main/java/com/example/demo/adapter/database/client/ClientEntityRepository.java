package com.example.demo.adapter.database.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {

    Set<ClientEntity> findByUserId(UUID userId);
}
