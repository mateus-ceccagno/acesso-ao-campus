package com.acessocampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroPontoNotFoundException extends Exception {
    
    public RegistroPontoNotFoundException(Long id) {
        super(String.format("Registro de ponto ID %s não encontrado!", id));
    }
}
