package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.JobsRepository;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.repository.PersonsMoviesRepository;
import com.example.demo.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonsMoviesService {


    private final PersonsMoviesRepository personsMoviesRepository;
    private final PersonsRepository personsRepository;
    private final MoviesRepository moviesRepository;
    private final JobsRepository jobsRepository;
    private final EmailService emailService;


    public PersonsMoviesEntity savePersonMovies(PersonsMoviesEntity personsMoviesEntity) {
        return personsMoviesRepository.save(personsMoviesEntity);
    }

    public Optional<PersonsMoviesEntity> findById(Long id) {
        return personsMoviesRepository.findById(id);
    }

    }



