package com.atividade.mutantes.AtivMutantes.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MutantInvitedDto {
    private String name;

    private String password;

    private String power;

    private Integer age;

    private Double aliensDefeated;

    private Double demonsDefeated;

    private String invite = "You are invited to join the E.S.P.A.D.A. organization!";
}
