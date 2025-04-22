package com.superbiblio.controller;

import com.superbiblio.model.Utilisateur;
import com.superbiblio.repository.UtilisateurRepository;
import com.superbiblio.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        return utilisateurOptional.map(utilisateur -> ResponseEntity.ok().body(utilisateur))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byEmail")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@RequestParam String email) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByAdresseEmail(email);
        return utilisateurOptional.map(utilisateur -> ResponseEntity.ok().body(utilisateur))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return ResponseEntity.ok().body(utilisateurs);
    }

    @PostMapping("/create")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateur.setActive(true);
        Utilisateur createdUtilisateur = utilisateurRepository.save(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateur);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur updatedUtilisateur) {
        if (!utilisateurRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedUtilisateur.setUtilisateurId(id);
        Utilisateur savedUtilisateur = utilisateurRepository.save(updatedUtilisateur);
        return ResponseEntity.ok().body(savedUtilisateur);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteUtilisateur(@PathVariable int id) {
        boolean success = utilisateurService.disableUtilisateur(id);
        if (success) {
            return ResponseEntity.ok("Utilisateur désactivé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé avec l'ID : " + id);
        }
    }
}

