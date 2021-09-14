package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Movies;
import com.example.demo.domain.dto.MoviesDto;
import com.example.demo.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviesService {
    private static Logger logger = LoggerFactory.getLogger(MoviesService.class);

    private final MoviesRepository moviesRepository;
    private final EmailService emailService;


    public long getMovieCount() {
        return moviesRepository.count();
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



    public void sendMovie(Movies movies) throws MessagingException {
        String content = "Film: " + movies.getTitle()
                + "\n - rok produkcji: " + movies.getProductionYear()
                + "\n - gatunek: " + movies.getGenre().getName();

        var list = new ArrayList<String>();
        list.add(content);

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        emailService.sendEmail("sturgulewskaanna@gmail.com", "Losowy film!", content);
    }
}
