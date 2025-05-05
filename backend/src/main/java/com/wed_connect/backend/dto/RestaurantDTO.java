package com.wed_connect.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces")
    private String address;

    private String capacity;

}