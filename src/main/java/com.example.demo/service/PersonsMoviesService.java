package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.JobsRepository;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.repository.PersonsMoviesRepository;
import com.example.demo.repository.PersonsRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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

    public PersonsMovies createPersonsMoviesJob(Movies movies, Persons persons, Jobs jobs) {
        PersonsMovies personsMovies = new PersonsMovies();
        personsMovies.setJobs(jobs);
        personsMovies.setPersons(persons);
        personsMovies.setMovies(movies);
        return savePersonMovies(personsMovies);
    }


     public void sendPhotoFilms(PersonsMovies personsMovies) throws MessagingException {
        String email  = personsMovies.getPersons().getPicturePath();
        String content = " ";
         emailService.sendEmail(email, "Zdjęcie do filmu", content);

    }
}


