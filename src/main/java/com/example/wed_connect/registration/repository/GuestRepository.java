package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Guest;
import com.example.wed_connect.registration.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findByEmail(String email);
    List<Guest> findByGuestWeddingsWeddingId(Long weddingId);
}
