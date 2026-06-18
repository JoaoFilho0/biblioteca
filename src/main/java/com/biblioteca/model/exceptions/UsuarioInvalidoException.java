package com.biblioteca.model.exceptions;

public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}
