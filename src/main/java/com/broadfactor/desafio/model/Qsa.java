package com.broadfactor.desafio.model;

import com.broadfactor.desafio.dto.QsaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.hibernate.dialect.Dialect;


import java.util.UUID;

@Table(name = "qsa")
@Entity(name = "Qsa")
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
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;

    public Qsa(QsaDTO qsa) {
        this.id = qsa.id();
        this.nome = qsa.nome();
        this.qual = qsa.qual();
        this.company = qsa.company();
    }
}