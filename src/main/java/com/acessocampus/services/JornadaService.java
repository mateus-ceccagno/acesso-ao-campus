package com.acessocampus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.JornadaMapper;
import com.acessocampus.dto.request.JornadaDTO;
import com.acessocampus.entities.Jornada;
import com.acessocampus.exceptions.JornadaNotFoundException;
import com.acessocampus.repositories.JornadaRepository;

@Service
public class JornadaService {
    
    private JornadaRepository jornadaRepository;
    private JornadaMapper jornadaMapper;

    public JornadaService(JornadaRepository jornadaRepository, JornadaMapper jornadaMapper) {
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }

    public List<JornadaDTO> listAll() {
        List<Jornada> jornadas = jornadaRepository.findAll();
        return jornadas.stream().map(jornadaMapper::toDTO).collect(Collectors.toList());
    }

    public JornadaDTO findById(Long id) throws JornadaNotFoundException {
        Jornada jornada = jornadaRepository.findById(id).orElseThrow(() -> new JornadaNotFoundException(id));
        return jornadaMapper.toDTO(jornada);
    }

    public void delete(Long id) throws JornadaNotFoundException {
        jornadaRepository.findById(id).orElseThrow(() -> new JornadaNotFoundException(id));
        jornadaRepository.deleteById(id);
    }

    public void create(JornadaDTO dto) {      
        Jornada Jornada = jornadaMapper.toModel(dto);
        jornadaRepository.save(Jornada);
    }
}
