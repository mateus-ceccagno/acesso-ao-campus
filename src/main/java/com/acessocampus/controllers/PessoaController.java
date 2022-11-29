package com.acessocampus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acessocampus.dto.request.PessoaDTO;
import com.acessocampus.dto.request.PessoaDTOPost;
import com.acessocampus.exceptions.PessoaNotFoundException;
import com.acessocampus.services.PessoaService;

@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
    
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<PessoaDTO> listAll() {
        return pessoaService.listAll();
    }

    @GetMapping("/{id}")
    public PessoaDTO findById(@PathVariable Long id) throws PessoaNotFoundException {
        return pessoaService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws PessoaNotFoundException {
        pessoaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PessoaDTOPost dto) {
        pessoaService.create(dto);
    }
}
