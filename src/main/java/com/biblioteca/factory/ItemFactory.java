package com.biblioteca.factory;

import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.Ebook;

import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

public class ItemFactory {
    private static final Map<String, Function<ItemRequestDTO, ItemBiblioteca>> CRIADORES = Map.of(
            "LIVRO", Livro::new,
            "REVISTA", Revista::new,
            "EBOOK", Ebook::new
    );

    public static ItemBiblioteca criarItem(ItemRequestDTO dto) {
        if (dto.tipo() == null) {
            throw new IllegalArgumentException("Tipo de item não pode ser nulo");
        }

        Function<ItemRequestDTO, ItemBiblioteca> criador =
                CRIADORES.get(dto.tipo().toUpperCase(Locale.ROOT));

        if (criador == null) {
            throw new IllegalArgumentException("Tipo de item inválido: " + dto.tipo());
        }

        return criador.apply(dto);
    }
}
