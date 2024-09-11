package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

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


    public Client updateClientInfo(Long clientId, String name, String phoneNumber) {
        Client client = findById(clientId);
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        return clientRepository.save(client);
    }
}
