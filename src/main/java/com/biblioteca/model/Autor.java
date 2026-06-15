package com.biblioteca.model;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import com.biblioteca.model.abstracts.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Autor extends Pessoa {
    private String nacionalidade;

    @ManyToMany(mappedBy = "autores")
    private Set<ItemBiblioteca> obras = new HashSet<>();
}
