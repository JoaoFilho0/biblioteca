package com.biblioteca.dto;

public record EmprestimoRequestDTO(
        String leitor,
        Integer diasParaDevolucao
) {
}
