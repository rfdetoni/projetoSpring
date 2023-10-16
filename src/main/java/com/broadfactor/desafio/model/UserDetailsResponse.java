package com.broadfactor.desafio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Table(name = "users")
@Entity(name = "UsersDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDetailsResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnpj_id")
    private Company company;


    public UserDetailsResponse(UserEntity byEmail) {
        this.id= byEmail.getId();
        this.name = byEmail.getName();
        this.email = byEmail.getEmail();;
        this.company = byEmail.getCompany();
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(Company company) {
        this.company= company;
    }

//    public void setData(JsonNode data) {
//        this.data = data;
//    }
}
