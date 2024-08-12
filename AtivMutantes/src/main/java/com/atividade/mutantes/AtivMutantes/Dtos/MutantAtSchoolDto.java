package com.atividade.mutantes.AtivMutantes.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MutantAtSchoolDto(@NotBlank String name, @NotNull Boolean atSchool) {
}
