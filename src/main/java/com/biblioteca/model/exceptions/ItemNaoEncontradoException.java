package com.biblioteca.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
