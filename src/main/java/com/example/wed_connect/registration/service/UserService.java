package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.*;
import com.example.wed_connect.registration.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final WeddingAgencyRepository weddingAgencyRepository;
    private final RestaurantRepository restaurantRepository;
    private final WeddingAgencyService weddingAgencyService;
    private final WeddingRepository weddingRepository;

    public UserService(UserRepository userRepository, ClientRepository clientRepository,
                       WeddingAgencyRepository weddingAgencyRepository, RestaurantRepository restaurantRepository,
                       WeddingAgencyService weddingAgencyService, WeddingRepository weddingRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.weddingAgencyRepository = weddingAgencyRepository;
        this.restaurantRepository = restaurantRepository;
        this.weddingAgencyService = weddingAgencyService;
        this.weddingRepository = weddingRepository;
    }

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
                Wedding wedding = new Wedding();
                wedding.setClient(client);
                weddingRepository.save(wedding);
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
        if (!userRepository.existsByUsername(user.getUsername()) || !userRepository.existsByPassword(user.getPassword())) {
            return "Invalid username or password";
        }

        return "User logged in successfully";
    }

    public String getUserType(User user) {
        Long userId = userRepository.findIdByUsername(user.getUsername());

        if (clientRepository.existsByUserId(userId)) {
            return "Client";
        } else if (weddingAgencyRepository.existsByUserId(userId)){
            return "Wedding Agency";
        } else if (restaurantRepository.existsByUserId(userId)){
            return "Restaurant";
        }
        return "No user found";
    }

    public Long returnUserId(User user) {
         return userRepository.findIdByUsername(user.getUsername());
    }
}
