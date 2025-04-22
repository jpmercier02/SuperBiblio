package com.superbiblio.controller;


import com.superbiblio.model.Auteur;
import com.superbiblio.repository.AuteursRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/authors")
public class AuteurController {

    @Autowired
    private AuteursRepository auteurRepository;

    @GetMapping("/{id}")
    public Optional<Auteur> getAuteurById(@PathVariable int id) {
        return auteurRepository.findById(id);
    }

    @GetMapping("/byNom/{nom}")
    public List<Auteur> getAuteurByNom(@PathVariable String nom) {
        return auteurRepository.findByNom(nom);
    }

    @GetMapping("/search")
    public List<Auteur> searchAuteursByNom(@RequestParam String nom) {
        return auteurRepository.findByNom(nom);
    }

    @GetMapping("/searchByNomPrenom")
    public List<Auteur> searchAuteurByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return auteurRepository.findByNomAndPrenomOrderByNomAscPrenomAsc(nom, prenom);
    }

    @GetMapping("/all")
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    @PostMapping("/create")
    public Auteur createAuteur(@Valid @RequestBody Auteur auteur, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Validation de la création " + errors.toString());
        }

        return auteurRepository.save(auteur);
    }

    @PutMapping("/update/{id}")
    public Auteur updateAuteur(@PathVariable int id, @RequestBody Auteur updatedAuteur) {
        if (auteurRepository.existsById(id)) {
            updatedAuteur.setAuteurId(id);
            return auteurRepository.save(updatedAuteur);
        } else {
            throw new RuntimeException("Auteur not found with id: " + id);
        }
    }
}
