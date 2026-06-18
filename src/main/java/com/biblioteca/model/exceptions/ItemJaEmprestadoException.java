package com.biblioteca.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemJaEmprestadoException extends RuntimeException {
    public ItemJaEmprestadoException(String mensagem) {
        super(mensagem);
    }
}
