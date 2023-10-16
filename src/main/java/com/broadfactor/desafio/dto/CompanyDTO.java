package com.broadfactor.desafio.dto;

import com.broadfactor.desafio.model.Activities;
import com.broadfactor.desafio.model.Qsa;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CompanyDTO(UUID id,
                         List<ActivitiesDTO> atividades_secundarias,
                         List<ActivitiesDTO> atividade_principal,
                         String dataSituacao,
                         String tipo,
                         String nome,
                         String telefone,
                         String email,
                         List<QsaDTO> qsa,
                         String situacao,
                         String bairro,
                         String logradouro,
                         String numero,
                         String cep,
                         String municipio,
                         String porte,
                         String abertura,
                         String naturezaJuridica,
                         String uf,
                         String cnpj,
                         OffsetDateTime ultimaAtualizacao,
                         String status,
                         String fantasia,
                         String complemento,
                         String efr,
                         String motivoSituacao,
                         String situacaoEspecial,
                         String dataSituacaoEspecial,
                         BigDecimal capitalSocial
) {
    public CompanyDTO(String cnpj) {
        this(null, null,
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                cnpj, null, null,
                null, null, null,
                null, null,null, BigDecimal.ZERO);
    }
}