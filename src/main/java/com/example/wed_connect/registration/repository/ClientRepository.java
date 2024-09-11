package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByUserId(Long userId);
    Optional<Client> findByUserId(Long userId);
}
