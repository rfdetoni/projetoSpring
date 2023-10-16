package com.broadfactor.desafio.service;


//import com.broadfactor.desafio.model.CnpjEntity;
import com.broadfactor.desafio.dto.CompanyDTO;
import com.broadfactor.desafio.model.Company;
import com.broadfactor.desafio.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class CnpjService {
    private final  String url = "https://receitaws.com.br";
    private final String uri = "/v1/cnpj/{cnpj}";
   // private final CompanyRepository companyRepository;


    public Company retornaCnpj(String cnpj) {
        CompanyDTO companyDTO = null;
        try {
            companyDTO = WebClient
                    .create(url)
                    .get()
                    .uri(uri, cnpj)
                    .retrieve()
                    .bodyToMono(CompanyDTO.class).block();
        } catch (NullPointerException e) {
            companyDTO = new CompanyDTO(cnpj);
        }
      return new Company(companyDTO);
    }
}
