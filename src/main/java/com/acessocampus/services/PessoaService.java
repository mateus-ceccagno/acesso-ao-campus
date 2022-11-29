package com.acessocampus.services;

import com.acessocampus.dto.mapper.JornadaMapper;
import com.acessocampus.dto.mapper.NivelAcessoMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.PessoaMapper;
import com.acessocampus.dto.request.PessoaDTO;
import com.acessocampus.dto.request.PessoaDTOPost;
import com.acessocampus.entities.Jornada;
import com.acessocampus.entities.NivelAcesso;
import com.acessocampus.entities.Pessoa;
import com.acessocampus.exceptions.PessoaNotFoundException;
import com.acessocampus.repositories.JornadaRepository;
import com.acessocampus.repositories.NivelAcessoRepository;
import com.acessocampus.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    private PessoaRepository pessoaRepository;
    private PessoaMapper pessoaMapper;
    private NivelAcessoRepository nivelAcesoRepository;
    private NivelAcessoMapper nivelAcessoMapper;
    private JornadaRepository jornadaRepository;
    private JornadaMapper jornadaMapper;

    public PessoaService(PessoaRepository pessoaRepository,
                         PessoaMapper pessoaMapper,
                         NivelAcessoRepository nivelAcesoRepository,
                         NivelAcessoMapper nivelAcessoMapper,
                         JornadaRepository jornadaRepository,
                         JornadaMapper jornadaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.nivelAcesoRepository = nivelAcesoRepository;
        this.nivelAcessoMapper = nivelAcessoMapper;
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }


    public List<PessoaDTO> listAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream().map(pessoaMapper::toDTO).collect(Collectors.toList());
    }

    public PessoaDTO findById(Long id) throws PessoaNotFoundException {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id));
        return pessoaMapper.toDTO(pessoa);
    }

    public void delete(Long id) throws PessoaNotFoundException {
        pessoaRepository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id));
        pessoaRepository.deleteById(id);
    }

    public void create(PessoaDTOPost dto) {
        NivelAcesso retNivelAcesso = nivelAcesoRepository.getById(dto.getIdNivelAcesso());
        Jornada retJornada = jornadaRepository.getById(dto.getIdNivelAcesso());
        
        PessoaDTO dtoSave = new PessoaDTO();
        dtoSave.setId(dto.getId());
        dtoSave.setNomeCompleto(dto.getNomeCompleto());
        dtoSave.setNivelAcesso(nivelAcessoMapper.toDTO(retNivelAcesso));
        dtoSave.setJornada(jornadaMapper.toDTO(retJornada));
        
        Pessoa pessoa = pessoaMapper.toModel(dtoSave);
        pessoaRepository.save(pessoa);
    }
}
