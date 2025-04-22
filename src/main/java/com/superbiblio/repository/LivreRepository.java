package com.superbiblio.repository;

import com.superbiblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Integer> {
    List<Livre> findByTitreContainingIgnoreCase(String titre);
}
