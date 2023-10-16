package com.broadfactor.desafio.model;

import com.broadfactor.desafio.dto.ActivitiesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Table(name = "activities")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners( AuditingEntityListener.class )
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties( { "hibernateLazyInitializer", "handler" } )
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    private String code;
    private String text;

    private Integer tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;

    public UUID getId() {
        return id;
    }

    public Activities(ActivitiesDTO activitiesDTO, Boolean isPrincipal) {
        this.id = activitiesDTO.id();
        this.code = activitiesDTO.code();
        this.text = activitiesDTO.text();
        this.tipo = isPrincipal ? 1 : 2;
        this.company = activitiesDTO.company();
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}