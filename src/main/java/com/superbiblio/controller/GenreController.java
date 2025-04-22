package com.superbiblio.controller;

import com.superbiblio.model.Genre;
import com.superbiblio.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        return genreOptional.map(genre -> ResponseEntity.ok().body(genre))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre createdGenre = genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre) {
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedGenre.setGenreId(id);
        Genre savedGenre = genreRepository.save(updatedGenre);
        return ResponseEntity.ok().body(savedGenre);
    }
}

