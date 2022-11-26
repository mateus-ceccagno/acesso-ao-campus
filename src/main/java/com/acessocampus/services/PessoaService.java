package com.acessocampus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acessocampus.dto.mapper.PessoaMapper;
import com.acessocampus.dto.request.PessoaDTO;
import com.acessocampus.entities.Pessoa;
import com.acessocampus.exceptions.PessoaNotFoundException;
import com.acessocampus.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    private PessoaRepository pessoaRepository;
    private PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
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

    public void create(PessoaDTO dto) {      
        Pessoa pessoa = pessoaMapper.toModel(dto);
        pessoaRepository.save(pessoa);
    }
}
