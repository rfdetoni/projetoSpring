package com.broadfactor.desafio.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record UserDTO(

        @Id
        UUID id,

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank

        String password,

        @Pattern(regexp = "\\d{14}")
        String cnpj) {
}
