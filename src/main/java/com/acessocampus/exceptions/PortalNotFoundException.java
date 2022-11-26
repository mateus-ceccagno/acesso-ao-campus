package com.acessocampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PortalNotFoundException extends Exception {
    
    public PortalNotFoundException(Long id) {
        super(String.format("Portal ID %s não encontrada!", id));
    }
}
