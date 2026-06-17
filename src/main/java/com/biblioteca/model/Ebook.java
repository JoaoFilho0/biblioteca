package com.biblioteca.model;

import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Ebook extends ItemBiblioteca {
    private String formato;
    private Double tamanhoArquivo;

    public Ebook() {

    }

    public Ebook(ItemRequestDTO dto) {
        super(dto);
        setFormato(dto.formato());
        setTamanhoArquivo(dto.tamanhoArquivo());
    }

    public void setFormato(String formato) {
        if (formato == null || formato.isBlank()) {
            throw new IllegalArgumentException("Formato não pode ser nulo ou vazio.");
        }

        this.formato = formato;
    }

    public void setTamanhoArquivo(Double tamanhoArquivo) {
        if (tamanhoArquivo == null) {
            throw new IllegalArgumentException("Tamanho do arquivo não pode ser nulo.");
        }

        if (tamanhoArquivo <= 0) {
            throw new IllegalArgumentException("Tamanho do arquivo deve ser maior que zero.");
        }

        this.tamanhoArquivo = tamanhoArquivo;
    }

    @Override
    public String getTipo() {
        return "Ebook";
    }
}
