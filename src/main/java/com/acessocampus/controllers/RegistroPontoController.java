package com.acessocampus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acessocampus.dto.request.RegistroPontoDTO;
import com.acessocampus.dto.request.RegistroPontoDTOGet;
import com.acessocampus.exceptions.RegistroPontoNotFoundException;
import com.acessocampus.exceptions.PortalNotFoundException;
import com.acessocampus.exceptions.PessoaNotFoundException;
import com.acessocampus.services.RegistroPontoService;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/registro-ponto")
public class RegistroPontoController {
    
    private RegistroPontoService registroPontoService;

    public RegistroPontoController(RegistroPontoService registroPontoService) {
        this.registroPontoService = registroPontoService;
    }

    @GetMapping
    public List<RegistroPontoDTOGet> listAll() throws IOException {
        return registroPontoService.listAll();
    }

    @GetMapping("/{id}")
    public RegistroPontoDTOGet findById(@PathVariable Long id) throws RegistroPontoNotFoundException {
        return registroPontoService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws RegistroPontoNotFoundException {
        registroPontoService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RegistroPontoDTO dto) 
        throws PessoaNotFoundException, PortalNotFoundException, IOException {
        registroPontoService.create(dto);
    }
}
