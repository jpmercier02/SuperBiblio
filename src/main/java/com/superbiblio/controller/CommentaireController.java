package com.superbiblio.controller;

import com.superbiblio.exceptions.NotFoundException;
import com.superbiblio.model.Commentaire;
import com.superbiblio.model.Livre;
import com.superbiblio.model.Utilisateur;
import com.superbiblio.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable int id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Commentaire not found with id: " + id));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Commentaire> getCommentairesByUtilisateurId(@PathVariable int utilisateurId) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUtilisateurId(utilisateurId);
        return commentaireRepository.findByUtilisateur(utilisateur);
    }

    @GetMapping("/livre/{livreId}")
    public List<Commentaire> getCommentairesByLivreId(@PathVariable int livreId) {
        Livre livre = new Livre();
        livre.setLivreId(livreId);
        return commentaireRepository.findByLivre(livre);
    }

    @PostMapping("/create")
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @PutMapping("/update/{id}")
    public Commentaire updateCommentaire(@PathVariable int id, @RequestBody Commentaire updatedCommentaire) {
        Commentaire existingCommentaire = commentaireRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Commentaire not found with id: " + id));

        existingCommentaire.setContenu(updatedCommentaire.getContenu());
        existingCommentaire.setDateCommentaire(updatedCommentaire.getDateCommentaire());
        existingCommentaire.setNote(updatedCommentaire.getNote());

        return commentaireRepository.save(existingCommentaire);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommentaire(@PathVariable int id) {
        commentaireRepository.deleteById(id);
    }
}

