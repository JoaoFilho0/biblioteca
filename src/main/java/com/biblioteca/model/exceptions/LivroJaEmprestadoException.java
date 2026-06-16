package com.biblioteca.model.exceptions;

public class LivroJaEmprestadoException extends RuntimeException {
    public LivroJaEmprestadoException(String mensagem) {
        super(mensagem);
    }
}
