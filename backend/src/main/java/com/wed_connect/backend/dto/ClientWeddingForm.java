package com.wed_connect.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWeddingForm {

    @Valid
    @NotNull
    private ClientDTO client;

    @Valid
    @NotNull
    private WeddingDTO wedding;
}