package com.wed_connect.backend.service;

import com.wed_connect.backend.dto.UserDTO;
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

    public String registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return "Username is already taken";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return "Passwords do not match";
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setType(userDTO.getType());

        userRepository.save(user);

        switch (userDTO.getType()) {
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

    public String authenticateUser(UserDTO userDTO) {
        Optional<User> foundUserOptional = userRepository.findByUsername(userDTO.getUsername());

        if (foundUserOptional.isEmpty()) {
            return "Invalid username or password";
        }

        User foundUser = foundUserOptional.get();

        if (!passwordEncoder.matches(userDTO.getPassword(), foundUser.getPassword())) {
            return "Invalid username or password";
        }

        return "User logged in successfully";
    }


    public boolean authenticate(String rawPassword, User user) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public String getUserType(String username) {
        Long userId = userRepository.findIdByUsername(username);

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
