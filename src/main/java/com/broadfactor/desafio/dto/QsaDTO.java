package com.broadfactor.desafio.dto;

import com.broadfactor.desafio.model.Company;

import java.util.UUID;
public record QsaDTO (  UUID id,
         String nome,
         String qual,
         Company company){
}
