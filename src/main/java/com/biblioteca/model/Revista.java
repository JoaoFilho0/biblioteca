package com.biblioteca.model;

import com.biblioteca.dto.ItemRequestDTO;
import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.interfaces.CalcularMulta;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Revista extends ItemBiblioteca implements CalcularMulta {
    private Integer edicao;
    private String periodicidade;

    public Revista() {

    }

    public Revista(ItemRequestDTO dto) {
        super(dto);
        setEdicao(dto.edicao());
        setPeriodicidade(dto.periodicidade());
    }

    public void setEdicao(Integer edicao) {
        if (edicao == null) {
            throw new IllegalArgumentException("Edição não pode ser nula.");
        }

        if (edicao <= 0) {
            throw new IllegalArgumentException("Edição deve ser maior que zero.");
        }

        this.edicao = edicao;
    }

    public void setPeriodicidade(String periodicidade) {
        if (periodicidade == null || periodicidade.isBlank()) {
            throw new IllegalArgumentException("Periodicidade não pode ser nula ou vazia.");
        }

        this.periodicidade = periodicidade;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public float calcularMulta(int diasAtraso) {
        return (float) (diasAtraso * 1.5);
    }
}
