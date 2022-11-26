package com.acessocampus.dto.mapper;

import org.mapstruct.Mapper;

import com.acessocampus.dto.request.NivelAcessoDTO;
import com.acessocampus.entities.NivelAcesso;

@Mapper(componentModel = "spring")
public interface NivelAcessoMapper {
    
    NivelAcesso toModel(NivelAcessoDTO dto);

    NivelAcessoDTO toDTO(NivelAcesso nivelAcesso);
}
