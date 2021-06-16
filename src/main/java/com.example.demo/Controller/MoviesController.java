package com.example.demo.Controller;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Movies;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.MoviesDto;
import com.example.demo.service.CountriesService;
import com.example.demo.service.GenreService;
import com.example.demo.service.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies") // localhost:8080/category
@CrossOrigin // Dodatkowe parametry http np potrzebne do swaggera!!! - poczytaj o TYM !!!!!
public class MoviesController {

    private final MoviesService moviesService;
    private final CountriesService countriesService;
    private final GenreService genreService;

    public MoviesController(MoviesService moviesService, CountriesService countriesService, GenreService genreService) {
        this.moviesService = moviesService;
        this.countriesService = countriesService;
        this.genreService = genreService;
    }

    // value = add_order
    // value = add_client
    // value = add.product

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
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
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
}
