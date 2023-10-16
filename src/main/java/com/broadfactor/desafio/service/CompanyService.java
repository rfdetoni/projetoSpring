package com.broadfactor.desafio.service;


//import com.broadfactor.desafio.model.CnpjEntity;
import com.broadfactor.desafio.dto.ActivitiesDTO;
import com.broadfactor.desafio.dto.CompanyDTO;
import com.broadfactor.desafio.dto.QsaDTO;
import com.broadfactor.desafio.model.Activities;
import com.broadfactor.desafio.model.Company;
import com.broadfactor.desafio.model.Qsa;
import com.broadfactor.desafio.repository.ActivitiesRepository;
import com.broadfactor.desafio.repository.CompanyRepository;
import com.broadfactor.desafio.repository.QsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyService {
    private final  String url = "https://receitaws.com.br";
    private final String uri = "/v1/cnpj/{cnpj}";

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    QsaRepository qsaRepository;


    public CompanyDTO retornaCnpj(String cnpj) {
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
      return companyDTO;
    }


    public Company save(String cnpj){

        var companyDTO =  retornaCnpj(cnpj);
        var companyObj = companyRepository.save(new Company(companyDTO));

        var activities = new ArrayList<Activities>();
        companyDTO.atividades_secundarias().forEach(atividade -> {
            activities.add(new Activities(atividade, false, companyObj));

        });
        companyDTO.atividade_principal().forEach(atividade -> {
            activities.add(new Activities(atividade, true, companyObj));
        });
        var qsaArray = new ArrayList<Qsa>();
        companyDTO.qsa().forEach(qsaItem ->{
            qsaArray.add(new Qsa(qsaItem, companyObj));
        });

        activitiesRepository.saveAll(activities);
        qsaRepository.saveAll(qsaArray);


        return  companyRepository.getReferenceById(companyObj.getId());
    }

    public Company update(String cnpj) {
        Company existingCompany = companyRepository.getReferenceByCnpj(cnpj);
        if (existingCompany == null) {
          return save(cnpj);
        }
        CompanyDTO companyDTO = retornaCnpj(cnpj);
        existingCompany.updateFromDTO(companyDTO);

        Company updatedCompany = companyRepository.save(existingCompany);

        List<Activities> activities = new ArrayList<>();
        for (ActivitiesDTO atividadeDTO : companyDTO.atividades_secundarias()) {
            Activities activity = new Activities(atividadeDTO, false, updatedCompany);
            activities.add(activity);
        }

        for (ActivitiesDTO atividadeDTO : companyDTO.atividade_principal()) {
            Activities activity = new Activities(atividadeDTO, true, updatedCompany);
            activities.add(activity);
        }

        activitiesRepository.saveAll(activities);

        List<Qsa> qsaList = new ArrayList<>();
        for (QsaDTO qsaDTO : companyDTO.qsa()) {
            Qsa qsa = new Qsa(qsaDTO, updatedCompany);
            qsaList.add(qsa);
        }
        qsaRepository.saveAll(qsaList);

        return updatedCompany;
    }
}




