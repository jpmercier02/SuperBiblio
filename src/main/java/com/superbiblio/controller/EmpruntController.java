package com.superbiblio.controller;

import com.superbiblio.model.Emprunt;
import com.superbiblio.model.Utilisateur;
import com.superbiblio.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntRepository empruntRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable int id) {
        Optional<Emprunt> empruntOptional = empruntRepository.findById(id);
        return empruntOptional.map(emprunt -> ResponseEntity.ok().body(emprunt))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Emprunt>> getEmpruntsByUtilisateurId(@PathVariable int utilisateurId) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUtilisateurId(utilisateurId);
        List<Emprunt> emprunts = empruntRepository.findByUtilisateur(utilisateur);
        return ResponseEntity.ok().body(emprunts);
    }

    @PostMapping("/create")
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody Emprunt emprunt) {
        Emprunt createdEmprunt = empruntRepository.save(emprunt);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmprunt);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable int id, @RequestBody Emprunt updatedEmprunt) {
        if (!empruntRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedEmprunt.setEmpruntId(id);
        Emprunt savedEmprunt = empruntRepository.save(updatedEmprunt);
        return ResponseEntity.ok().body(savedEmprunt);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable int id) {
        if (!empruntRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empruntRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

