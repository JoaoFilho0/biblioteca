package com.biblioteca.model.interfaces;

import com.biblioteca.model.exceptions.LivroJaEmprestadoException;

public interface Emprestavel {
    void emprestar() throws LivroJaEmprestadoException;
    void devolver();
    boolean estaEmprestado();
}
