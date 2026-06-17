package com.biblioteca.dto;

public record MultaResponseDTO(
        Long itemId,
        String itemTitulo,
        String itemTipo,
        int diasAtraso,
        float valorMulta
) {
}
