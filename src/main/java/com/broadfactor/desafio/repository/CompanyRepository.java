package com.broadfactor.desafio.repository;

import com.broadfactor.desafio.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company getReferenceByCnpj(String cnpj);
}