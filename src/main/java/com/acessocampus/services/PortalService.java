package com.acessocampus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.PortalMapper;
import com.acessocampus.dto.request.PortalDTO;
import com.acessocampus.entities.Portal;
import com.acessocampus.exceptions.PortalNotFoundException;
import com.acessocampus.repositories.PortalRepository;

@Service
public class PortalService {
    
    private PortalRepository portalRepository;
    private PortalMapper portalMapper;

    public PortalService(PortalRepository portalRepository, PortalMapper portalMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
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

    public void create(PortalDTO dto) {      
        Portal portal = portalMapper.toModel(dto);
        portalRepository.save(portal);
    }
}
