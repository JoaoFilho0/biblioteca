package com.biblioteca.dto;

import com.biblioteca.model.Ebook;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.abstracts.ItemBiblioteca;

public record ItemResponseDTO(
        Long id,
        String tipo,
        String titulo,
        Integer anoPublicacao,
        String isbn,
        Integer numeroPaginas,
        Integer edicao,
        String periodicidade,
        String formato,
        Double tamanhoArquivo
) {
    public static ItemResponseDTO fromEntity(ItemBiblioteca item) {
        return switch (item) {
            case Livro livro -> new ItemResponseDTO(
                    livro.getId(),
                    livro.getTipo(),
                    livro.getTitulo(),
                    livro.getAnoPublicacao(),
                    livro.getIsbn(),
                    livro.getNumeroPaginas(),
                    null,
                    null,
                    null,
                    null
            );

            case Revista revista -> new ItemResponseDTO(
                    revista.getId(),
                    revista.getTipo(),
                    revista.getTitulo(),
                    revista.getAnoPublicacao(),
                    null,
                    null,
                    revista.getEdicao(),
                    revista.getPeriodicidade(),
                    null,
                    null
            );

            case Ebook ebook -> new ItemResponseDTO(
                    ebook.getId(),
                    ebook.getTipo(),
                    ebook.getTitulo(),
                    ebook.getAnoPublicacao(),
                    null,
                    null,
                    null,
                    null,
                    ebook.getFormato(),
                    ebook.getTamanhoArquivo()
            );

            default -> throw new IllegalArgumentException("Tipo de item não suportado.");
        };
    }
}