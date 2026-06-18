package com.biblioteca.repository;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    boolean existsByItemIdAndStatusIn(Long itemId, Collection<StatusEmprestimo> statuses);

    Optional<Emprestimo> findFirstByItemIdAndStatusIn(Long itemId, Collection<StatusEmprestimo> statuses);
}
