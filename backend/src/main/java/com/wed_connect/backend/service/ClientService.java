package com.wed_connect.backend.service;

import com.wed_connect.backend.dto.ClientDTO;
import com.wed_connect.backend.exception.ClientNotFoundException;
import com.wed_connect.backend.model.Client;
import com.wed_connect.backend.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public Client findById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + clientId));
    }

    public Client findByUserId(Long userId) {
        return clientRepository.findByUserId(userId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found for user ID: " + userId));
    }

    public Long getClientIdByUsername(String username) {
        Long userId = userService.returnUserIdByUsername(username);
        return clientRepository.findByUserId(userId)
                .map(Client::getId)
                .orElseThrow(() -> new ClientNotFoundException("No client found for username: " + username));
    }

    @Transactional
    public void updateClientProfile(Long clientId, ClientDTO clientDTO) {
        Client client = findById(clientId);
        client.setName(clientDTO.getName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        clientRepository.save(client);
    }

    public ClientDTO getClientDTOById(Long clientId) {
        Client client = findById(clientId);
        return convertToClientDTO(client);
    }

    private ClientDTO convertToClientDTO(Client client) {
        if (client == null) return null;
        return new ClientDTO(client.getId(), client.getName(), client.getPhoneNumber());
    }
}
