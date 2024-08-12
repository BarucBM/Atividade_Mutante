package com.atividade.mutantes.AtivMutantes.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "devs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevsModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MutantModel mutant;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime entry;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime departure;
}
