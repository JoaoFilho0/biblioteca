package com.biblioteca.model;

import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.interfaces.CalcularMulta;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Livro extends ItemBiblioteca implements CalcularMulta {
    private String isbn;
    private Integer numeroPaginas;

    public Livro() {

    }

    public Livro(ItemRequestDTO dto) {
        super(dto);
        setIsbn(dto.isbn());
        setNumeroPaginas(dto.numeroPaginas());
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN não pode ser nulo ou vazio.");
        }

        this.isbn = isbn;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        if (numeroPaginas == null) {
            throw new IllegalArgumentException("Número de páginas não pode ser nulo.");
        }

        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("Número de páginas deve ser maior que zero.");
        }

        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public float calcularMulta(int diasAtraso) {
        return diasAtraso * 2;
    }
}
