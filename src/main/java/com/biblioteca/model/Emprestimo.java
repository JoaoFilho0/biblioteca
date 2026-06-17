package com.biblioteca.model;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;

    private LocalDate dataPrevistaDevolucao;

    private LocalDate dataDevolucao;

    private String leitor;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemBiblioteca item;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("Data de empréstimo não pode ser nula.");
        }

        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        if (dataPrevistaDevolucao == null) {
            throw new IllegalArgumentException("Data prevista de devolução não pode ser nula.");
        }

        if (dataEmprestimo != null && dataPrevistaDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data prevista de devolução não pode ser anterior ao empréstimo.");
        }

        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        if (dataDevolucao != null && dataEmprestimo != null && dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolução não pode ser anterior ao empréstimo.");
        }

        this.dataDevolucao = dataDevolucao;
    }

    public void setLeitor(String leitor) {
        if (leitor == null || leitor.isBlank()) {
            throw new IllegalArgumentException("Leitor não pode ser nulo ou vazio.");
        }

        this.leitor = leitor;
    }

    public void setStatus(StatusEmprestimo status) {
        if (status == null) {
            throw new IllegalArgumentException("Status do empréstimo não pode ser nulo.");
        }

        this.status = status;
    }

    public void setItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("Item do empréstimo não pode ser nulo.");
        }

        this.item = item;
    }
}
