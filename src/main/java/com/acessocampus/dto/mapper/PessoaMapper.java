package com.acessocampus.dto.mapper;

import org.mapstruct.Mapper;

import com.acessocampus.dto.request.PessoaDTO;
import com.acessocampus.entities.Pessoa;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    
    Pessoa toModel(PessoaDTO dto);

    PessoaDTO toDTO(Pessoa pessoa);
}
