package com.broadfactor.desafio.model;

import com.broadfactor.desafio.dto.QsaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Table(name = "qsa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Qsa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    private String nome;
    private String qual;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    @JsonIgnore
    private Company company;

    public Qsa(QsaDTO qsa, Company comp) {
        this.id = qsa.id();
        this.nome = qsa.nome();
        this.qual = qsa.qual();
        this.company = comp;
    }

    public void setCompany(Company company){
        this.company = company;
    }
}