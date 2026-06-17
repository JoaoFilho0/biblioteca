package com.biblioteca.model;

import com.biblioteca.model.abstracts.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario extends Pessoa {
    private String matricula;

    @OneToMany(mappedBy = "usuario")
    private Set<Emprestimo> emprestimos = new HashSet<>();
}
