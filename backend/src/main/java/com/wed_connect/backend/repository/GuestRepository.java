package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.Guest;
import com.wed_connect.backend.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findByEmail(String email);
    List<Guest> findByGuestWeddingsWeddingId(Long weddingId);
}
