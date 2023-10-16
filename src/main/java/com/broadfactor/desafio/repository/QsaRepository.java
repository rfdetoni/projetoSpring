package com.broadfactor.desafio.repository;

import com.broadfactor.desafio.model.Qsa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QsaRepository extends JpaRepository<Qsa, UUID> {
}