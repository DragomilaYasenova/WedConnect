package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.model.Guest;
import com.example.wed_connect.registration.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RuntimeException("Client not found with ID: " + clientId);
        }
        return client.get();
    }

    public Client findByUserId(Long userId) {
        Optional<Client> client = clientRepository.findByUserId(userId);

        if (client.isEmpty()) {
            throw new RuntimeException("Client not found for User ID: " + userId);
        }
        return client.get();
    }


    public void updateClientProfile(Long clientId, String name, String phoneNumber) {
        Client client = findById(clientId);
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        clientRepository.save(client);
    }
}
