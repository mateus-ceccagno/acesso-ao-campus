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

import com.acessocampus.dto.request.PortalDTO;
import com.acessocampus.exceptions.PortalNotFoundException;
import com.acessocampus.services.PortalService;

@RestController
@RequestMapping("/api/v1/portal")
public class PortalController {
    
    private PortalService portalService;

    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }

    @GetMapping
    public List<PortalDTO> listAll() {
        return portalService.listAll();
    }

    @GetMapping("/{id}")
    public PortalDTO findById(@PathVariable Long id) throws PortalNotFoundException {
        return portalService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws PortalNotFoundException {
        portalService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PortalDTO dto) {
        portalService.create(dto);
    }
}
