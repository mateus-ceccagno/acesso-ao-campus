package com.acessocampus.dto.mapper;

import org.mapstruct.Mapper;

import com.acessocampus.dto.request.PortalDTO;
import com.acessocampus.entities.Portal;

@Mapper(componentModel = "spring")
public interface PortalMapper {
    
    Portal toModel(PortalDTO dto);

    PortalDTO toDTO(Portal portal);
}
