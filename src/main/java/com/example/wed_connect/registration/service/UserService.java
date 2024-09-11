package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.*;
import com.example.wed_connect.registration.repository.ClientRepository;
import com.example.wed_connect.registration.repository.RestaurantRepository;
import com.example.wed_connect.registration.repository.UserRepository;
import com.example.wed_connect.registration.repository.WeddingAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WeddingAgencyRepository weddingAgencyRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public String registerUser(User user, UserType userType) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username is already taken";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        user.setType(userType);
        userRepository.save(user);

        switch (userType) {
            case CLIENT:
                Client client = new Client();
                client.setUser(user);
                clientRepository.save(client);
                break;

            case WEDDING_AGENCY:
                WeddingAgency wag = new WeddingAgency();
                wag.setUser(user);
                weddingAgencyRepository.save(wag);
                break;

            case RESTAURANT:
                Restaurant restaurant = new Restaurant();
                restaurant.setUser(user);
                restaurantRepository.save(restaurant);
                break;

            default:
                throw new IllegalArgumentException("Invalid user type");
        }

        return "User registered successfully";
    }

    public String authenticateUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            return "Invalid username";
        }

        if (!userRepository.existsByPassword(user.getPassword())) {
            return "Invalid password";
        }

        return "User logged in successfully";
    }

    public String getUserType(User user) {
        Long userId = userRepository.findIdByUsername(user.getUsername());

        if (clientRepository.existsByUserId(userId)) {
            return "Client";
        }
        return "No user found";
    }

    public Long returnUserId(User user) {
         return userRepository.findIdByUsername(user.getUsername());
    }
}
