package com.biblioteca.model.exceptions;

public class LivroJaEmprestadoException extends Exception {
    public LivroJaEmprestadoException(String mensagem) {
        super(mensagem);
    }
}
