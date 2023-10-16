package com.broadfactor.desafio.controller;

import com.broadfactor.desafio.dto.UserDTO;
import com.broadfactor.desafio.dto.UserDetailsDTO;
import com.broadfactor.desafio.model.UserDetailsResponse;
import com.broadfactor.desafio.model.UserEntity;
import com.broadfactor.desafio.repository.UserRepository;
import com.broadfactor.desafio.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> update(@PathVariable UUID id, @RequestBody UserDTO dados){
       var valida = userRepository.getReferenceById(id);
       if (valida != null){
            var updated = userService.update(id, dados);
            if (updated != null){
                return ResponseEntity.ok(new UserDetailsDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getCompany()));
            }
       }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<UserDetailsDTO> retrieve(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String subject = userDetails.getUsername();
        var entity = userRepository.findByEmail(subject);
        if(entity != null) {
            return ResponseEntity.ok(new UserDetailsDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCompany()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        var user = userRepository.getReferenceById(id);
        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
