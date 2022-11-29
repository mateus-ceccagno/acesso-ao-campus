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
public class PortalDTOPost {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;

    @Valid
    @NotNull
    private Long idNivelAcesso;
}
