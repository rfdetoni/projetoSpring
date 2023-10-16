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
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Activities> atividade;
    private String dataSituacao;
    private String tipo;
    private String nome;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        return id != null && Objects.equals(id, company.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Company(CompanyDTO company) {
        this.id = company.id();

        var activities = new ArrayList<Activities>();
        company.atividades_secundarias().forEach(atividade -> {
            activities.add(new Activities(atividade, false));
        });

        company.atividade_principal().forEach(atividade -> {
            activities.add(new Activities(atividade, true));
        });
        this.atividade = activities;

        this.dataSituacao = company.dataSituacao();
        this.tipo = company.tipo();
        this.nome = company.nome();
        this.telefone = company.telefone();
        this.email = company.email();
        var qsaArray = new ArrayList<Qsa>();
        company.qsa().forEach(qsaItem ->{
            qsaArray.add(new Qsa(qsaItem));
        });

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
}