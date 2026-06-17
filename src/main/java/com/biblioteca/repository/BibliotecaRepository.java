package com.biblioteca.repository;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BibliotecaRepository {

    //ArrayList e HashMap para busca por ID
    private final List<ItemBiblioteca> listaItens;
    private final Map<Long, ItemBiblioteca> mapaItens;

    public BibliotecaRepository() {
        this.listaItens = new ArrayList<>();
        this.mapaItens = new HashMap<>();
    }


    public void cadastrar(ItemBiblioteca item) {
        if (item != null) {
            listaItens.add(item);

            // Se o item já tiver um ID, isso indexa no HashMap para buscas rápidas
            if (item.getId() != null) {
                mapaItens.put(item.getId(), item);
            }

            System.out.println("[" + item.getTipo() + "] '" + item.getTitulo() + "' guardado nas coleções locais.");
        }
    }


    public ItemBiblioteca buscar(Long id) throws Exception {
        ItemBiblioteca item = mapaItens.get(id);

        if (item == null) {
            throw new Exception("O item com o ID '" + id + "' não foi encontrado nas coleções.");
        }

        return item;
    }


    public void listar() {
        if (listaItens.isEmpty()) {
            System.out.println("Nenhum item cadastrado nas coleções.");
            return;
        }

        System.out.println("\n=== LISTAGEM DE ITENS (ArrayList) ===");
        for (ItemBiblioteca item : listaItens) {
            System.out.println("ID: " + item.getId() +
                    " | Tipo: " + item.getTipo() +
                    " | Título: " + item.getTitulo() +
                    " | Ano: " + item.getAnoPublicacao());
        }
    }

    public List<ItemBiblioteca> getListaItens() {
        return this.listaItens;
    }
}
