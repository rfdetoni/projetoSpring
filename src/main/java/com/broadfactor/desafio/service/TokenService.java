package com.broadfactor.desafio.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.broadfactor.desafio.model.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
  //  @Value("secret")
    private String secret;
    public String generateToken(UserEntity data){
        try{
            UUID uuid = UUID.randomUUID();
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Desafio BroadFactor")
                    .withSubject(data.getEmail())
                    .withClaim("session", uuid.toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }catch(JWTCreationException e){
            throw  new RuntimeException("Erro ao gerar token JWT",e);
        }

    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Desafio BroadFactor")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
