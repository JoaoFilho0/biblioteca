package com.biblioteca.factory;

import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.Ebook;

public class ItemFactory {

    public static ItemBiblioteca criarItem(ItemRequestDTO dto) {
        return switch (dto.tipo().toUpperCase()) {
            case "LIVRO" -> new Livro(dto);
            case "REVISTA" -> new Revista(dto);
            case "EBOOK" -> new Ebook(dto);
            default -> throw new IllegalArgumentException("Tipo de item inválido: " + dto.tipo());
        };
    }
}
