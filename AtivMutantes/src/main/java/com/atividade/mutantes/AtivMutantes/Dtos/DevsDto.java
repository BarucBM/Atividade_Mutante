package com.atividade.mutantes.AtivMutantes.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;


public record DevsDto(@NotBlank String mutantName, @JsonFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime entry, @JsonFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime departure) {
}
