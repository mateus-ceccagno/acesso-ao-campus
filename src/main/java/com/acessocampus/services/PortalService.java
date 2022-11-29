package com.acessocampus.services;

import com.acessocampus.dto.mapper.NivelAcessoMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.PortalMapper;
import com.acessocampus.dto.request.PortalDTO;
import com.acessocampus.dto.request.PortalDTOPost;
import com.acessocampus.entities.NivelAcesso;
import com.acessocampus.entities.Portal;
import com.acessocampus.exceptions.PortalNotFoundException;
import com.acessocampus.repositories.NivelAcessoRepository;
import com.acessocampus.repositories.PortalRepository;

@Service
public class PortalService {
    
    private PortalRepository portalRepository;
    private PortalMapper portalMapper;
    private NivelAcessoRepository nivelAcesoRepository;
    private NivelAcessoMapper nivelAcessoMapper;

    public PortalService(PortalRepository portalRepository, PortalMapper portalMapper, NivelAcessoRepository nivelAcesoRepository, NivelAcessoMapper nivelAcessoMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
        this.nivelAcesoRepository = nivelAcesoRepository;
        this.nivelAcessoMapper = nivelAcessoMapper;
    }

    public List<PortalDTO> listAll() {
        List<Portal> portais = portalRepository.findAll();

        return portais.stream().map(portalMapper::toDTO).collect(Collectors.toList());
    }

    public PortalDTO findById(Long id) throws PortalNotFoundException {
        Portal portal = portalRepository.findById(id).orElseThrow(() -> new PortalNotFoundException(id));
        return portalMapper.toDTO(portal);
    }

    public void delete(Long id) throws PortalNotFoundException {
        portalRepository.findById(id).orElseThrow(() -> new PortalNotFoundException(id));
        portalRepository.deleteById(id);
    }

    public void create(PortalDTOPost dto) {
        NivelAcesso retNivelAcesso = nivelAcesoRepository.getById(dto.getIdNivelAcesso());
        
        PortalDTO dtoSave = new PortalDTO();
        dtoSave.setId(dto.getId());
        dtoSave.setNome(dto.getNome());
        dtoSave.setNivelAcesso(nivelAcessoMapper.toDTO(retNivelAcesso));
        
        Portal portal = portalMapper.toModel(dtoSave);
        portalRepository.save(portal);
    }
}
