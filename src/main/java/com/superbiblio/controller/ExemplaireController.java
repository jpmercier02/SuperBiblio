package com.superbiblio.controller;

import com.superbiblio.model.Exemplaire;
import com.superbiblio.model.Livre;
import com.superbiblio.repository.EmpruntRepository;
import com.superbiblio.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exemplaires")
public class ExemplaireController {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Exemplaire> getExemplaireById(@PathVariable int id) {
        Optional<Exemplaire> exemplaireOptional = exemplaireRepository.findById(id);
        return exemplaireOptional.map(exemplaire -> ResponseEntity.ok().body(exemplaire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/livre/{livreId}")
    public ResponseEntity<List<Exemplaire>> getExemplairesByLivreId(@PathVariable int livreId) {
        Livre livre = new Livre();
        livre.setLivreId(livreId);
        List<Exemplaire> exemplaires = exemplaireRepository.findByLivre(livre);
        return ResponseEntity.ok().body(exemplaires);
    }

    @PostMapping("/create")
    public ResponseEntity<Exemplaire> createExemplaire(@RequestBody Exemplaire exemplaire) {
        Exemplaire createdExemplaire = exemplaireRepository.save(exemplaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExemplaire);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Exemplaire> updateExemplaire(@PathVariable int id, @RequestBody Exemplaire updatedExemplaire) {
        if (!exemplaireRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedExemplaire.setExemplaireId(id);
        Exemplaire savedExemplaire = exemplaireRepository.save(updatedExemplaire);
        return ResponseEntity.ok().body(savedExemplaire);
    }

}

