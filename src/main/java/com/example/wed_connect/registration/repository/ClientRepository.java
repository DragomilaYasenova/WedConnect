package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
