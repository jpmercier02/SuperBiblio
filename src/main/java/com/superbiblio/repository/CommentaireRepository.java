package com.superbiblio.repository;

import com.superbiblio.model.Commentaire;
import com.superbiblio.model.Livre;
import com.superbiblio.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    List<Commentaire> findByUtilisateur(Utilisateur utilisateur);

    List<Commentaire> findByLivre(Livre livre);
}
