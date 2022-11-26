package com.acessocampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JornadaNotFoundException extends Exception {
    
    public JornadaNotFoundException(Long id) {
        super(String.format("Jornada ID %s não encontrada!", id));
    }
}
