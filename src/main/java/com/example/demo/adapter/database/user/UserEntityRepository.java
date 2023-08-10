package com.example.demo.adapter.database.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);
}
