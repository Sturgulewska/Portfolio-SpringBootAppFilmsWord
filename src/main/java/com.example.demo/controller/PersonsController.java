package com.example.demo.controller;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Movies;
import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.domain.dto.SavePersonPictureDto;
import com.example.demo.service.CountriesService;
import com.example.demo.service.JobService;
import com.example.demo.service.MoviesService;
import com.example.demo.service.PersonsService;
import com.example.demo.service.utils.PersonPictureValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonsController {

    private final PersonsService personsService;
    private final MoviesService moviesService;
    private final JobService jobService;
    private final CountriesService countriesService;

    public PersonsController(PersonsService personsService, MoviesService moviesService, JobService jobService, CountriesService countriesService) {
        this.personsService = personsService;
        this.moviesService = moviesService;
        this.jobService = jobService;
        this.countriesService = countriesService;
    }

    @RequestMapping(
            value = "/getFindPersons/{personId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getFindPerson(@PathVariable("personId") Long personId) {
        Optional<Persons> personsOptional = personsService.findByIdPersons(personId);
        return new ResponseEntity<>(personsOptional, HttpStatus.OK);
    }

    @RequestMapping(value = "/add_person",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> addPerson(@RequestBody @Valid PersonsDto personsDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }
        Optional<Countries> optionalCountries = countriesService.findById(personsDto.getCountriesId());
        if (optionalCountries.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono kraju", "countries_id "), HttpStatus.BAD_REQUEST);
        }
        personsService.createPersons(personsDto, optionalCountries.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/set_person_picture",
            method = RequestMethod.POST,
            produces = MediaType.IMAGE_JPEG_VALUE)

    public ResponseEntity<Object> setPersonPicture(@ModelAttribute @Valid SavePersonPictureDto dto, BindingResult br) throws IOException {
        if (br.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            br.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }

        Optional<Persons> optionalPerson = personsService.findByIdPersons(dto.getPersonId());
        if (optionalPerson.isEmpty()) {
            ErrorDto errorDto = new ErrorDto("Podany użytkownik nie istnieje!", "personId");
            return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }

        PersonPictureValidator validator = new PersonPictureValidator();
        ErrorDto validateResult = validator.validateFile(dto.getFile());
        if (validateResult.isError()) {
            return new ResponseEntity<>(validateResult, HttpStatus.BAD_REQUEST);
        }

        personsService.savePersonPicture(optionalPerson.get(), dto.getFile());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sendPersonPhoto/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Object> sendPersonPhoto(@PathVariable("personId") Long personId) throws MessagingException {
        Optional<Persons> optionalPersons = personsService.findByIdPersons(personId);
        if (optionalPersons.isEmpty()) {
            return new ResponseEntity<>("Podane zagadnienie, nie istnieje w bazie!!", HttpStatus.BAD_REQUEST);
        }

        Persons persons = optionalPersons.get();
        personsService.sendPhotoFilms(persons);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMovie/{movieId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getPersonsMovies(@PathVariable("movieId") Long movieId) {
        Optional<Movies> optionalPersonsMovies = moviesService.findById(movieId);
        if (optionalPersonsMovies.isEmpty()) {
            return new ResponseEntity<>("Podany film, nie istnieje w bazie!!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(optionalPersonsMovies, HttpStatus.OK);
    }
}
