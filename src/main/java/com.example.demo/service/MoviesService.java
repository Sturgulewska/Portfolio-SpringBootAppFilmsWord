package com.example.demo.service;

import com.example.demo.domain.CountriesEntity;
import com.example.demo.domain.GenreEntity;
import com.example.demo.domain.MoviesEntity;
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

    public MoviesEntity createMovie(MoviesDto moviesDto, CountriesEntity countries, GenreEntity genreEntity) {
        MoviesEntity moviesEntity = new MoviesEntity();
        moviesEntity.setTitle(moviesDto.getTitle());
        moviesEntity.setGenreEntity(genreEntity);
        moviesEntity.setProductionYear(moviesDto.getProductionYear());
        moviesEntity.setCountries(countries);

        return saveMovies(moviesEntity);
    }
    public Optional<MoviesEntity> findById(Long id) {
        return moviesRepository.findById(id);
    }

    public MoviesEntity saveMovies(MoviesEntity moviesEntity) {
        return moviesRepository.save(moviesEntity);
    }



    public void sendMovie(MoviesEntity moviesEntity) throws MessagingException {
        String content = "Film: " + moviesEntity.getTitle()
                + "\n - rok produkcji: " + moviesEntity.getProductionYear()
                + "\n - gatunek: " + moviesEntity.getGenreEntity().getName();

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
