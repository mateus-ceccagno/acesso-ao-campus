package com.acessocampus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.NivelAcessoMapper;
import com.acessocampus.dto.request.NivelAcessoDTO;
import com.acessocampus.entities.NivelAcesso;
import com.acessocampus.exceptions.NivelAcessoNotFoundException;
import com.acessocampus.repositories.NivelAcessoRepository;

@Service
public class NivelAcessoService {
    
    private NivelAcessoRepository nivelAcessoRepository;
    private NivelAcessoMapper nivelAcessoMapper;

    public NivelAcessoService(NivelAcessoRepository nivelAcessoRepository, NivelAcessoMapper nivelAcessoMapper) {
        this.nivelAcessoRepository = nivelAcessoRepository;
        this.nivelAcessoMapper = nivelAcessoMapper;
    }

    public List<NivelAcessoDTO> listAll() {
        List<NivelAcesso> niveisAcesso = nivelAcessoRepository.findAll();
        return niveisAcesso.stream().map(nivelAcessoMapper::toDTO).collect(Collectors.toList());
    }

    public NivelAcessoDTO findById(Long id) throws NivelAcessoNotFoundException {
        NivelAcesso nivelAcesso = nivelAcessoRepository.findById(id).orElseThrow(() -> new NivelAcessoNotFoundException(id));
        return nivelAcessoMapper.toDTO(nivelAcesso);
    }

    public void delete(Long id) throws NivelAcessoNotFoundException {
        nivelAcessoRepository.findById(id).orElseThrow(() -> new NivelAcessoNotFoundException(id));
        nivelAcessoRepository.deleteById(id);
    }

    public void create(NivelAcessoDTO dto) {      
        NivelAcesso nivelAcesso = nivelAcessoMapper.toModel(dto);
        nivelAcessoRepository.save(nivelAcesso);
    }
}
