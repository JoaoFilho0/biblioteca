package com.biblioteca.model;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ebook extends ItemBiblioteca {
    private String formato;
    private Double tamanhoArquivo;

    @Override
    public String getTipo() {
        return "Ebook";
    }
}
