package com.broadfactor.desafio.service;

import com.broadfactor.desafio.dto.ResponseDTO;
import com.broadfactor.desafio.model.Company;
import com.broadfactor.desafio.model.UserEntity;
import com.broadfactor.desafio.dto.UserDTO;
import com.broadfactor.desafio.repository.CompanyRepository;
import com.broadfactor.desafio.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@Component
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    public ResponseEntity create(UserDTO dados, Company company) {
        UUID id;
        try {
            var user=  userRepository.findByEmail(dados.email());
            if (user != null){
                return new ResponseEntity(new ResponseDTO("Email já cadastrado",null), HttpStatus.BAD_REQUEST);
            }
            String encryptedPass = passwordEncoder.encode(dados.password());
            UserDTO newData = new UserDTO(null,dados.name(),dados.email(),encryptedPass, dados.cnpj() );
            id = userRepository.save(new UserEntity(newData, company)).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            return new ResponseEntity(new ResponseDTO("Erro ao realizar cadastro, contate o administrador do sistema",null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ResponseDTO("Cadastro Efetuado com sucesso",id), HttpStatus.OK);
    }

    public UserEntity update(UUID id, UserDTO dados) {
        Optional<UserEntity> existingUserOptional = Optional.ofNullable(userRepository.getReferenceById(id));

        if (existingUserOptional.isEmpty()) {
           return null;
        }

        UserEntity existingUser = existingUserOptional.get();

        Company savedCompany = companyRepository.getReferenceByCnpj(dados.cnpj());
        if (!dados.cnpj().isEmpty() & !dados.cnpj().equals(existingUser.getCompany().getCnpj())) {
           if (savedCompany == null) {
               savedCompany = companyService.update(dados.cnpj());
           }
        }
        existingUser.setName(dados.name().isEmpty() ? existingUser.getName() : dados.name());
        existingUser.setEmail(dados.email().isEmpty() ? existingUser.getEmail() : dados.email());
        existingUser.setPassword(dados.password().isEmpty() ? existingUser.getPassword() : dados.password());
        existingUser.setCompany(savedCompany);


        return userRepository.save(existingUser);
    }
}