package com.broadfactor.desafio.dto;

import com.broadfactor.desafio.model.Company;

import java.util.UUID;

public record ActivitiesDTO( UUID id,
            String code,
            String text,
            Integer tipo,
            Company company) {
}
