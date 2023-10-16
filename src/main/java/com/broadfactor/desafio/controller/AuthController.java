package com.broadfactor.desafio.controller;

import com.broadfactor.desafio.dto.AuthDTO;
import com.broadfactor.desafio.dto.TokenDTO;
import com.broadfactor.desafio.service.TokenService;
import com.broadfactor.desafio.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity login(@RequestBody AuthDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(token);
        var jwtToken = tokenService
                .generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(jwtToken));
    }
}

