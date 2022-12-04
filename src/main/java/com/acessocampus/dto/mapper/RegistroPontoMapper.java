package com.acessocampus.dto.mapper;

import org.mapstruct.Mapper;

import com.acessocampus.dto.request.RegistroPontoDTO;
import com.acessocampus.dto.request.RegistroPontoDTOGet;
import com.acessocampus.entities.RegistroPonto;

@Mapper(componentModel = "spring")
public interface RegistroPontoMapper {
    
    /**
     *
     * @param dto
     * @return
     */
    RegistroPonto toModel(RegistroPontoDTO dto);

    
    RegistroPontoDTOGet toDTO(RegistroPonto registroPonto);
}
