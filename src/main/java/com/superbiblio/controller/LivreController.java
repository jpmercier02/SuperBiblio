package com.superbiblio.controller;

import com.superbiblio.model.Livre;
import com.superbiblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable int id) {
        Optional<Livre> livreOptional = livreRepository.findById(id);
        return livreOptional.map(livre -> ResponseEntity.ok().body(livre))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreRepository.findAll();
        return ResponseEntity.ok().body(livres);
    }

    @GetMapping("/livres/search/{titre}")
    public List<Livre> searchLivresByTitre(@PathVariable String titre) {
        return livreRepository.findByTitreContainingIgnoreCase(titre);
    }

    @PostMapping("/create")
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre createdLivre = livreRepository.save(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivre);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable int id, @RequestBody Livre updatedLivre) {
        if (!livreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedLivre.setLivreId(id);
        Livre savedLivre = livreRepository.save(updatedLivre);
        return ResponseEntity.ok().body(savedLivre);
    }

}

