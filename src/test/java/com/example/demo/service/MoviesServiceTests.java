package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.CountriesRepository;
import com.example.demo.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MoviesServiceTests {


    private MoviesRepository moviesRepository = Mockito.mock(MoviesRepository.class);
    private EmailService emailService = Mockito.mock(EmailService.class);

    @Test
    void shouldFindMoviesId() {
        //Given
        MoviesService moviesService = new MoviesService(moviesRepository, emailService);
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Science Fiction");

        Countries countries = new Countries();
        countries.setId(1L);
        countries.setName("USA");

        Jobs jobs = new Jobs();
        jobs.setId(1L);
        jobs.setName("Actor");
        List<Jobs> jobsList = new ArrayList<>();

        Persons persons = new Persons();
        persons.setId(1L);
        persons.setFirstName("Natalie");
        persons.setLastName("Portmann");
        persons.setCountries(countries);
        persons.setEMail("natalie@wp.pl");
        persons.setPicturePath("C://Users//HP//Desktop//JAVA - projekty//movie_aplication//upload");
        persons.setJobsList(jobsList);

        List<Persons>personsList = new ArrayList<>();

        Movies movies = new Movies();

        movies.setId(1L);
        movies.setTitle("Star Wars");
        movies.setGenre(genre);
        movies.setProductionYear("1977");
        movies.setCountries(countries);
        movies.setPersonList(personsList);

        //When and Then
        Mockito.when(moviesRepository.findById(1L)).thenReturn(Optional.of(movies));
    }

}