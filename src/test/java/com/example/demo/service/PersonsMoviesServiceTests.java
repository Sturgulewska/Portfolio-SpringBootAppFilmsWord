package com.example.demo.service;

import com.example.demo.domain.PersonsMovies;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.repository.PersonsMoviesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonsMoviesServiceTests {

    private PersonsMoviesRepository personsMoviesRepository = Mockito.mock(PersonsMoviesRepository.class);

    @Test
    void shouldFindPersonsMoviesId(){
        //Given
        PersonsMoviesService personsMoviesService = new PersonsMoviesService(personsMoviesRepository);

        PersonsMovies personsMovies = new PersonsMovies();
        personsMovies.setPersonId(1L);
        personsMovies.setMovieId(1L);
        personsMovies.setId(1L);
        personsMovies.setJobs(1L);

        Mockito.when(personsMoviesRepository.findById(1L)).thenReturn(Optional.of(personsMovies));
    }
}