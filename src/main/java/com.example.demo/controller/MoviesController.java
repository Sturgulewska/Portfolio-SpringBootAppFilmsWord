package com.example.demo.controller;

import com.example.demo.domain.CountriesEntity;
import com.example.demo.domain.GenreEntity;
import com.example.demo.domain.MoviesEntity;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.MoviesDto;
import com.example.demo.service.CountriesService;
import com.example.demo.service.GenreService;
import com.example.demo.service.MoviesService;
import com.example.demo.service.ValidateBindingResult;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/movies") // localhost:8080/category
@CrossOrigin // Dodatkowe parametry http np potrzebne do swaggera!
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;
    private final CountriesService countriesService;
    private final GenreService genreService;

    // value = add_order
    // value = add_client
    // value = add.product

    @RequestMapping(
            value = "/getMovieId/{movieId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Object> getMovieId(@PathVariable("movieId") Long id) {
        Optional<MoviesEntity> moviesOptional = moviesService.findById(id);
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

        Optional<CountriesEntity> optionalCountries = countriesService.findById(moviesDto.getCountrieId());
        if (optionalCountries.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono kraju", "countries_id "), HttpStatus.BAD_REQUEST);
        }
        Optional<GenreEntity> optionalGenre = genreService.findByIdGenre(moviesDto.getGenreId());
        if (optionalGenre.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono gatunku", "genre_id "), HttpStatus.BAD_REQUEST);
        }
        moviesService.createMovie(moviesDto, optionalCountries.get(), optionalGenre.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value = "sendEmail/{MovieId}" , method = RequestMethod.GET)
    public ResponseEntity<Object>sendEmail(@PathVariable("MovieId") Long id) throws MessagingException {
        var optionalMovie = moviesService.findById(id);
        moviesService.sendMovie(optionalMovie.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
