package com.acessocampus.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nomeCompleto;

    @Valid
    @NotNull
    private JornadaDTO jornada;

    @Valid
    @NotNull
    private NivelAcessoDTO nivelAcesso;
}
