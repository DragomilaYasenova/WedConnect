package com.wed_connect.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeddingDTO {
    private Long id;

    @NotBlank(message = "Bride's name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nameBride;

    @NotBlank(message = "Groom's name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nameGroom;

    @NotBlank(message = "Bride's phone number is required")
    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumberBride;

    @NotBlank(message = "Groom's phone number is required")
    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumberGroom;

    @NotNull(message = "Wedding date is required")
    @FutureOrPresent(message = "Wedding date cannot be in the past")
    private LocalDate dateWedding;

    @NotBlank(message = "Location is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces")
    private String location;

    @Min(value = 0, message = "Can't be negative")
    @Max(value = 3000, message = "The number of guests cannot exceed 3000")
    private Integer numberOfGuests;

    private Long restaurantId;
}