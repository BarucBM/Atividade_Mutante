package com.atividade.mutantes.AtivMutantes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mutant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutantModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String password;

    private String power;

    private Integer age;

    private Double aliensDefeated;

    private Double demonsDefeated;

    @JsonIgnore
    private Boolean atSchool = false;

    @JsonIgnore
    private Boolean atEspada = false;

    @OneToMany(mappedBy = "mutant")
    @JsonIgnore
    private List<DevsModel> devs;
}
