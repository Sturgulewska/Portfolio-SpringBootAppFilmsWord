package com.example.demo.service;

import com.example.demo.domain.Genre;
import com.example.demo.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return saveGenre(genre);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Optional<Genre> findByIdGenre(Long id) {
        return genreRepository.findById(id);
    }
}
