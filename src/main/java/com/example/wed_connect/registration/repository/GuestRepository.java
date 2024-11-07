package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByWeddingId(Long weddingId);

    Optional<Guest> findByEmail(String email);
}
