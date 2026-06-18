package com.biblioteca.repository;

import com.biblioteca.model.abstracts.ItemBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemBibliotecaRepository extends JpaRepository<ItemBiblioteca, Long> {
}
