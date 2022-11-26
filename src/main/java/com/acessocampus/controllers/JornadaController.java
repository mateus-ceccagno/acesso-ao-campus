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

import com.acessocampus.dto.request.JornadaDTO;
import com.acessocampus.exceptions.JornadaNotFoundException;
import com.acessocampus.services.JornadaService;

@RestController
@RequestMapping("/api/v1/jornada")
public class JornadaController {
    
    private JornadaService jornadaService;

    public JornadaController( JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @GetMapping
    public List<JornadaDTO> listAll() {
        return jornadaService.listAll();
    }

    @GetMapping("/{id}")
    public JornadaDTO findById(@PathVariable Long id) throws JornadaNotFoundException {
        return jornadaService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws JornadaNotFoundException {
        jornadaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid JornadaDTO dto) {
        jornadaService.create(dto);
    }
}
