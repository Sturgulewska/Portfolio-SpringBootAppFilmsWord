package com.example.demo.Controller;

import com.example.demo.domain.Jobs;
import com.example.demo.domain.Movies;
import com.example.demo.domain.Persons;
import com.example.demo.domain.PersonsMovies;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/person_movies_control")
public class PersonMoviesController {

    private final PersonsMoviesService personsMoviesService;
    private final EmailService emailService;
    private final MoviesService moviesService;
    private final JobService jobService;
    private final PersonsService personsService;


    public PersonMoviesController(PersonsMoviesService personsMoviesService, EmailService emailService, MoviesService moviesService, JobService jobService, PersonsService personsService) {
        this.personsMoviesService = personsMoviesService;
        this.emailService = emailService;
        this.moviesService = moviesService;
        this.jobService = jobService;
        this.personsService = personsService;
    }

    @RequestMapping(
            value = "/getPersonsMoviesID/{personsMoviesId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Object> getPersonsMovies(@PathVariable("personsMoviesId") Long personsMoviesID) {
        Optional<PersonsMovies> optionalPersonsMovies = personsMoviesService.findById(personsMoviesID);
        if (optionalPersonsMovies.isEmpty()) {
            return new ResponseEntity<>("Podane zagadnienie, nie istnieje w bazie!!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optionalPersonsMovies, HttpStatus.OK);
    }

    @RequestMapping(value = "/personMovies/add_personMovies/{personId}/{movieId}{jobId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addPersonMovies(@PathVariable("personId") Long personId, @PathVariable("movieId") Long movieId,
                                                  @PathVariable("jobId") Long jobId){
        Optional<Movies> optionalMovies = moviesService.findById(movieId);
        if (optionalMovies.isEmpty()) {
            return new ResponseEntity<>("Podany film " + movieId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        Optional<Persons> optionalPersons = personsService.findByIdPersons(personId);
        if (optionalPersons.isEmpty()) {
            return new ResponseEntity<>("Podana osoba  " + personId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        Optional<Jobs> optionalJobs = jobService.findById(jobId);
        if (optionalPersons.isEmpty()) {
            return new ResponseEntity<>("Podana osoba  " + jobId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        personsMoviesService.createPersonsMoviesJob(optionalMovies.get(), optionalPersons.get(), optionalJobs.get());
        return new ResponseEntity<>(HttpStatus.OK);

}

    @RequestMapping(value = "/sendPersonMoviePhoto/{personMovieId}", method = RequestMethod.GET)
    public ResponseEntity<Object> sendPersonMovie(@PathVariable("personMovieId") Long personMovieId) throws MessagingException {
        Optional<PersonsMovies> optionalPersonsMovies = personsMoviesService.findById(personMovieId);
        if (optionalPersonsMovies.isEmpty()) {
            return new ResponseEntity<>("Podane zagadnienie, nie istnieje w bazie!!", HttpStatus.BAD_REQUEST);
        }

        PersonsMovies personsMovies = optionalPersonsMovies.get();
        personsMoviesService.sendPhotoFilms(personsMovies);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
