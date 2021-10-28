package com.example.demo.controller;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Movies;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.MoviesDto;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MoviesController {

    private final MoviesService moviesService;
    private final CountriesService countriesService;
    private final GenreService genreService;
    private final UserService userService;

    public MoviesController(MoviesService moviesService, CountriesService countriesService, GenreService genreService, UserService userService) {
        this.moviesService = moviesService;
        this.countriesService = countriesService;
        this.genreService = genreService;
        this.userService = userService;
    }

    @RequestMapping(
            value = "/getMovieId/{movieId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Object> getMovieId(@PathVariable("movieId") Long id) {
        Optional<Movies> moviesOptional = moviesService.findById(id);
        return new ResponseEntity<Object>(moviesOptional, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMovie",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> addMovie(@RequestBody @Valid MoviesDto moviesDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> validateErrorDtoList = ValidateBindingResult.validateBindingResult(bindingResult);
            if(!validateErrorDtoList.isEmpty())
            return new ResponseEntity<>(validateErrorDtoList, HttpStatus.BAD_REQUEST);
        }

        Optional<Countries> optionalCountries = countriesService.findById(moviesDto.getCountrieId());
        if (optionalCountries.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono kraju", "countries_id "), HttpStatus.BAD_REQUEST);
        }
        Optional<Genre> optionalGenre = genreService.findByIdGenre(moviesDto.getGenreId());
        if (optionalGenre.isEmpty()) {

            return new ResponseEntity<>(new ErrorDto("Nie znaleziono gatunku", "genre_id "), HttpStatus.BAD_REQUEST);
        }
        moviesService.createMovie(moviesDto, optionalCountries.get(), optionalGenre.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value = "sendEmail/{MovieId}/{UserId}" , method = RequestMethod.GET)
    public ResponseEntity<Object>sendEmail(@PathVariable("MovieId") Long id, @PathVariable("UserId") Long userId) throws MessagingException {
        var optionalMovie = moviesService.findById(id);
        var  optionalUser = userService.findById(userId);
        moviesService.sendMovie(optionalMovie.get(), optionalUser.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
