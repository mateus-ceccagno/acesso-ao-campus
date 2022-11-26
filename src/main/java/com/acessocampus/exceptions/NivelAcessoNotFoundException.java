package com.acessocampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NivelAcessoNotFoundException extends Exception {
    public NivelAcessoNotFoundException(Long id) {
        super(String.format("Nível de acesso ID %s não encontrado!", id));
    }
}
