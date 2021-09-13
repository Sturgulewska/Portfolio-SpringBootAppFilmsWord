package com.example.demo.service;

import com.example.demo.domain.GenreEntity;
import com.example.demo.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreEntity createGenre(String name) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(name);
        return saveGenre(genreEntity);
    }

    public GenreEntity saveGenre(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    public Optional<GenreEntity> findByIdGenre(Long id) {
        return genreRepository.findById(id);
    }
}
