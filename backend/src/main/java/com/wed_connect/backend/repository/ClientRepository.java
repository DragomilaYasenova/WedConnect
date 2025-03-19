package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByUserId(Long userId);
    Optional<Client> findByUserId(Long userId);
}
