package com.biblioteca.model;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.interfaces.CalcularMulta;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Revista extends ItemBiblioteca implements CalcularMulta {
    private Integer edicao;
    private String periodicidade;

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public float calcularMulta(int diasAtraso) {
        return (float) (diasAtraso * 1.5);
    }
}
