package com.broadfactor.desafio.model;

import com.broadfactor.desafio.dto.CompanyDTO;
import com.broadfactor.desafio.dto.QsaDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.OffsetDateTime;
import java.util.*;

@Table(name = "company")
@Entity(name = "Company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "company",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Activities> atividade;
    private String dataSituacao;
    private String tipo;
    private String nome;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "company",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)

    private List<Qsa> qsa;
    private String situacao;
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;
    private String municipio;
    private String porte;
    private String abertura;
    private String naturezaJuridica;
    private String uf;
    @Column(unique=true)
    private String cnpj;
    private OffsetDateTime ultimaAtualizacao;
    private String status;
    private String fantasia;
    private String complemento;
    private String efr;
    private String motivoSituacao;
    private String situacaoEspecial;
    private String dataSituacaoEspecial;
    private BigDecimal capitalSocial;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Company company = (Company) o;
        return company != null && Objects.equals(company, company.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Company(CompanyDTO company) {
        this.id = company.id();
        this.dataSituacao = company.dataSituacao();
        this.tipo = company.tipo();
        this.nome = company.nome();
        this.telefone = company.telefone();
        this.email = company.email();
        this.situacao = company.situacao();
        this.bairro = company.bairro();
        this.logradouro = company.logradouro();
        this.numero = company.numero();
        this.cep = company.cep();
        this.municipio = company.municipio();
        this.porte = company.porte();
        this.abertura = company.abertura();
        this.naturezaJuridica = company.naturezaJuridica() ;
        this.uf = company.uf();
        this.cnpj = company.cnpj().replace(".","")
                .replace("-","")
                .replace("/","");
        this.ultimaAtualizacao = company.ultimaAtualizacao();
        this.status = company.status();
        this.fantasia = company.fantasia();
        this.complemento = company.complemento();
        this.efr = company.efr();
        this.motivoSituacao = company.motivoSituacao();
        this.situacaoEspecial = company.situacaoEspecial();
        this.dataSituacaoEspecial = company.dataSituacaoEspecial();
        this.capitalSocial = company.capitalSocial();
    }

    public String getCnpj() {
        return cnpj;
    }


    public UUID getId() {
        return id;
    }

    public void updateFromDTO(CompanyDTO companyDTO) {
        this.dataSituacao = companyDTO.dataSituacao();
        this.tipo = companyDTO.tipo();
        this.nome = companyDTO.nome();
        this.telefone = companyDTO.telefone();
        this.email = companyDTO.email();
        this.situacao = companyDTO.situacao();
        this.bairro = companyDTO.bairro();
        this.logradouro = companyDTO.logradouro();
        this.numero = companyDTO.numero();
        this.cep = companyDTO.cep();
        this.municipio = companyDTO.municipio();
        this.porte = companyDTO.porte();
        this.abertura = companyDTO.abertura();
        this.naturezaJuridica = companyDTO.naturezaJuridica();
        this.uf = companyDTO.uf();
        this.cnpj = companyDTO.cnpj().replace(".", "").replace("-", "").replace("/", "");
        this.ultimaAtualizacao = companyDTO.ultimaAtualizacao();
        this.status = companyDTO.status();
        this.fantasia = companyDTO.fantasia();
        this.complemento = companyDTO.complemento();
        this.efr = companyDTO.efr();
        this.motivoSituacao = companyDTO.motivoSituacao();
        this.situacaoEspecial = companyDTO.situacaoEspecial();
        this.dataSituacaoEspecial = companyDTO.dataSituacaoEspecial();
        this.capitalSocial = companyDTO.capitalSocial();
    }
}