
package com.acessocampus.dto.mapper;

import org.mapstruct.Mapper;

import com.acessocampus.dto.request.JornadaDTO;
import com.acessocampus.entities.Jornada;

@Mapper(componentModel = "spring")
public interface JornadaMapper {

    Jornada toModel(JornadaDTO dto);

    JornadaDTO toDTO(Jornada jornada);
}