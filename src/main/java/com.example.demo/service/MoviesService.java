package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Movies;
import com.example.demo.domain.dto.MoviesDto;
import com.example.demo.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }


    public Movies createMovie(MoviesDto moviesDto, Countries countries, Genre genre) {
        Movies movies = new Movies();
        movies.setTitle(moviesDto.getTitle());
        movies.setGenre(genre);
        movies.setProductionYear(moviesDto.getProductionYear());
        movies.setCountries(countries);

        return saveMovies(movies);
    }
    public Optional<Movies> findById(Long id) {
        return moviesRepository.findById(id);
    }

    public Movies saveMovies(Movies movies) {
        return moviesRepository.save(movies);
    }
}
