package com.broadfactor.desafio.repository;

import com.broadfactor.desafio.model.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivitiesRepository extends JpaRepository<Activities, UUID> {
}