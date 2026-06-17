package com.biblioteca;

import com.biblioteca.model.Livro;
import com.biblioteca.service.BibliotecaService;

public class Main {

    public static void main(String[] args) {

        Livro livro = new Livro();

        livro.setTitulo("Programação Orientada a Objetos");
        livro.setAnoPublicacao(2026);

        BibliotecaService service = new BibliotecaService();

        service.salvar(livro);

        System.out.println("Cadastro realizado.");

        Livro carregado =
                service.carregar(Livro.class);

        if (carregado != null) {
            System.out.println("Livro carregado:");
            System.out.println(carregado.getTitulo());
        }
    }
}