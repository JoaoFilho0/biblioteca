package com.biblioteca.dto;

public record ItemRequestDTO(
        String tipo,
        String titulo,
        Integer anoPublicacao,
        String isbn,
        Integer numeroPaginas,
        Integer edicao,
        String periodicidade,
        String formato,
        Double tamanhoArquivo
) {}