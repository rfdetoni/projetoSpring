package com.broadfactor.desafio.controller;

import com.broadfactor.desafio.dto.UserDTO;
import com.broadfactor.desafio.model.Company;
import com.broadfactor.desafio.repository.CompanyRepository;
import com.broadfactor.desafio.repository.UserRepository;
import com.broadfactor.desafio.service.CnpjService;
import com.broadfactor.desafio.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class SignUpController {
    @Autowired
    private UserService userService;

    @Autowired
    CnpjService cnpjService;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody UserDTO dados){
        Company savedCompany = companyRepository.getReferenceByCnpj(dados.cnpj());
        if (savedCompany == null) {
            savedCompany = companyRepository.save(cnpjService.retornaCnpj(dados.cnpj()));
        }
        return userService.create(dados, savedCompany);
    }


}
