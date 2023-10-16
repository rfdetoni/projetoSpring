package com.broadfactor.desafio.dto;


import com.broadfactor.desafio.model.Company;
import com.broadfactor.desafio.model.UserEntity;

import java.util.UUID;

public record UserDetailsDTO(UUID id,
                             String name,
                             String email,
                             Company company) {
    public UserDetailsDTO(UUID id, String name, String email, Company company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
    }
}
