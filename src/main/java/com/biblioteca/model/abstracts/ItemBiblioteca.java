package com.biblioteca.model.abstracts;

import com.biblioteca.dto.ItemRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemBiblioteca {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Integer anoPublicacao;

    public ItemBiblioteca() {

    }

    public ItemBiblioteca(ItemRequestDTO dto) {
        setTitulo(dto.titulo());
        setAnoPublicacao(dto.anoPublicacao());
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio.");
        }

        this.titulo = titulo;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        if (anoPublicacao == null) {
            throw new IllegalArgumentException("Ano de publicação não pode ser nulo.");
        }

        if (anoPublicacao <= 0) {
            throw new IllegalArgumentException("Ano de publicação deve ser maior que zero.");
        }

        this.anoPublicacao = anoPublicacao;
    }

    public abstract String getTipo();
}
