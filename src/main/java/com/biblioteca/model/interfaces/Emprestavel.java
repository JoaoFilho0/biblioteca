package com.biblioteca.model.interfaces;

import com.biblioteca.model.exceptions.LivroJaEmprestadoException;

public interface Emprestavel {
    void emprestar();
    void devolver();
    boolean estaEmprestado();
}
