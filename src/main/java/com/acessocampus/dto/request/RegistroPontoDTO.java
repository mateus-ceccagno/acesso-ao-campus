package com.acessocampus.dto.request;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPontoDTO {
    private Long id;

    @NotNull
    private Long pessoaId;

    @NotNull
    private Long portalId;

    @Pattern(regexp = "^[E|S]{1}$", message ="Valores válidos 'E' ou 'S'")
    private String  EntradaSaida;   
}
