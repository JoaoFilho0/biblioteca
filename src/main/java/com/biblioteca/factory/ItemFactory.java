package com.biblioteca.factory;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.Ebook;

public class ItemFactory {

    public static ItemBiblioteca criarItem(String tipo, String titulo, Integer anoPublicacao, String infoEspecifica1, String infoEspecifica2) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do item não pode ser nulo.");
        }

        switch (tipo.toUpperCase()) {
            case "LIVRO":
                Livro livro = new Livro();
                livro.setTitulo(titulo);
                livro.setAnoPublicacao(anoPublicacao);
                livro.setIsbn(infoEspecifica1);
                if (infoEspecifica2 != null) {
                    livro.setNumeroPaginas(Integer.parseInt(infoEspecifica2));
                }
                return livro;

            case "REVISTA":
                Revista revista = new Revista();
                revista.setTitulo(titulo);
                revista.setAnoPublicacao(anoPublicacao);
                if (infoEspecifica1 != null) {
                    revista.setEdicao(Integer.parseInt(infoEspecifica1));
                }
                revista.setPeriodicidade(infoEspecifica2);
                return revista;

            case "EBOOK":
                Ebook ebook = new Ebook();
                ebook.setTitulo(titulo);
                ebook.setAnoPublicacao(anoPublicacao);
                ebook.setFormato(infoEspecifica1);
                if (infoEspecifica2 != null) {
                    ebook.setTamanhoArquivo(Double.parseDouble(infoEspecifica2));
                }
                return ebook;

            default:
                throw new IllegalArgumentException("Tipo de item inválido: " + tipo);
        }
    }
}
