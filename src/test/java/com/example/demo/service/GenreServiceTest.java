package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Genre;
import com.example.demo.repository.CountriesRepository;
import com.example.demo.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GenreServiceTest {

    private GenreRepository genreRepository = Mockito.mock(GenreRepository.class);


    @Test
    void shouldfindGenreId() {
        GenreService genreService = new GenreService(genreRepository);
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Thriller");

        //When and Then
        Mockito.when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));



    }
}