package com.atividade.mutantes.AtivMutantes.Dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutantDto {

    private String name;

    private String password;

    private String power;

    private Integer age;

    private Double aliensDefeated;

    private Double demonsDefeated;

    private Boolean atSchool;

    public MutantDto(@NotBlank String name, @NotBlank String password, @NotBlank String power, @NotNull Integer age, @NotNull Integer enemiesDefeated) {
        this.name = name;
        this.password = password;
        this.power = power;
        this.age = age;
        this.aliensDefeated = enemiesDefeated * 0.268;
        this.demonsDefeated = enemiesDefeated * 0.432;
        this.atSchool = false;
    }
}
