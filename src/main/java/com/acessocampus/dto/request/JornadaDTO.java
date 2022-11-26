package com.acessocampus.dto.request;

import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JornadaDTO {
    
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private LocalTime tempoTolerancia;
}
