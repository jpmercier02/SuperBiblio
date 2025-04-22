package com.superbiblio.repository;

import com.superbiblio.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuteursRepository extends JpaRepository<Auteur, Integer> {
    List<Auteur> findByNom(String nom);
    List<Auteur> findByNomAndPrenomOrderByNomAscPrenomAsc(String nom, String prenom);
}
