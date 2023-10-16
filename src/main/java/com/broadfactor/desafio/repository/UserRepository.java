package com.broadfactor.desafio.repository;

import com.broadfactor.desafio.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByEmail(String email);




    UserEntity getReferenceById(UUID id);
}
