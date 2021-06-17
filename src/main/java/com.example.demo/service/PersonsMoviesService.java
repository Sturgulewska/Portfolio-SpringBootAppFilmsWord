package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.JobsRepository;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.repository.PersonsMoviesRepository;
import com.example.demo.repository.PersonsRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.Optional;

@Service
public class PersonsMoviesService {

    private final PersonsMoviesRepository personsMoviesRepository;
    private final PersonsRepository personsRepository;
    private final MoviesRepository moviesRepository;
    private final JobsRepository jobsRepository;
    private final EmailService emailService;


    public PersonsMoviesService(PersonsMoviesRepository personsMoviesRepository, EmailService emailService, PersonsRepository personsRepository, MoviesRepository moviesRepository, JobsRepository jobsRepository, EmailService emailService1) {
        this.personsMoviesRepository = personsMoviesRepository;
        this.personsRepository = personsRepository;
        this.moviesRepository = moviesRepository;
        this.jobsRepository = jobsRepository;
        this.emailService = emailService1;
    }

    public PersonsMovies savePersonMovies(PersonsMovies personsMovies) {
        return personsMoviesRepository.save(personsMovies);
    }

    public Optional<PersonsMovies> findById(Long id) {
        return personsMoviesRepository.findById(id);
    }

    }



