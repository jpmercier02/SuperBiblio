package com.superbiblio.repository;

import com.superbiblio.model.Exemplaire;
import com.superbiblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    List<Exemplaire> findByLivre(Livre livre);
}
