package com.biblioteca.dto;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.StatusEmprestimo;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        Long itemId,
        String itemTitulo,
        String itemTipo,
        String leitor,
        LocalDate dataEmprestimo,
        LocalDate dataPrevistaDevolucao,
        LocalDate dataDevolucao,
        StatusEmprestimo status
) {
    public static EmprestimoResponseDTO fromEntity(Emprestimo emprestimo) {
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getItem().getId(),
                emprestimo.getItem().getTitulo(),
                emprestimo.getItem().getTipo(),
                emprestimo.getLeitor(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataDevolucao(),
                emprestimo.getStatus()
        );
    }
}
