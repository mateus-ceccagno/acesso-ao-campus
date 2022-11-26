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

import com.acessocampus.dto.request.NivelAcessoDTO;
import com.acessocampus.exceptions.NivelAcessoNotFoundException;
import com.acessocampus.services.NivelAcessoService;

@RestController
@RequestMapping("/api/v1/nivel-acesso")
public class NivelAcessoController {
    
    private NivelAcessoService nivelAcessoService;

    public NivelAcessoController(NivelAcessoService nivelAcessoService) {
        this.nivelAcessoService = nivelAcessoService;
    }

    @GetMapping
    public List<NivelAcessoDTO> listAll() {
        return nivelAcessoService.listAll();
    }

    @GetMapping("/{id}")
    public NivelAcessoDTO findById(@PathVariable Long id) throws NivelAcessoNotFoundException {
        return nivelAcessoService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NivelAcessoNotFoundException {
        nivelAcessoService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid NivelAcessoDTO dto) {
        nivelAcessoService.create(dto);
    }
}
