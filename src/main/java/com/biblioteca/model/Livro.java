package com.biblioteca.model;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.interfaces.CalcularMulta;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro extends ItemBiblioteca implements CalcularMulta {
    private String isbn;
    private Integer numeroPaginas;

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public float calcularMulta(int diasAtraso) {
        return diasAtraso * 2;
    }
}
