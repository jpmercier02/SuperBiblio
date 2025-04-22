package com.superbiblio.repository;

import com.superbiblio.model.Emprunt;
import com.superbiblio.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    List<Emprunt> findByUtilisateur(Utilisateur utilisateur);
}
