package com.wed_connect.backend.service;

import com.wed_connect.backend.model.*;
import com.wed_connect.backend.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final WeddingAgencyRepository weddingAgencyRepository;
    private final RestaurantRepository restaurantRepository;
    private final WeddingRepository weddingRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, ClientRepository clientRepository,
                       WeddingAgencyRepository weddingAgencyRepository, RestaurantRepository restaurantRepository,
                       WeddingRepository weddingRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.weddingAgencyRepository = weddingAgencyRepository;
        this.restaurantRepository = restaurantRepository;
        this.weddingRepository = weddingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(User user, UserType userType) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username is already taken";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setType(userType);
        userRepository.save(user);

        switch (userType) {
            case CLIENT:
                Client client = new Client();
                client.setUser(user);
                clientRepository.save(client);
                Wedding wedding = new Wedding();
                wedding.setClient(client);
                wedding.setDateWedding(LocalDate.now());
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
        Optional<User> foundUserOptional = userRepository.findByUsername(user.getUsername());

        if (foundUserOptional.isEmpty()) {
            return "Invalid username or password";
        }

        User foundUser = foundUserOptional.get();

        if (!authenticate(user.getPassword(), foundUser)) {
            return "Invalid username or password";
        }

        return "User logged in successfully";
    }

    public boolean authenticate(String rawPassword, User user) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public String getUserType(User user) {
        Long userId = userRepository.findIdByUsername(user.getUsername());

        if (clientRepository.existsByUserId(userId)) {
            return "Client";
        } else if (weddingAgencyRepository.existsByUserId(userId)) {
            return "Wedding Agency";
        } else if (restaurantRepository.existsByUserId(userId)) {
            return "Restaurant";
        }
        return "No user found";
    }

    public Long returnUserId(User user) {
        return userRepository.findIdByUsername(user.getUsername());
    }

    public Long returnUserIdByUsername(String username) {
        return userRepository.findIdByUsername(username);
    }
}
