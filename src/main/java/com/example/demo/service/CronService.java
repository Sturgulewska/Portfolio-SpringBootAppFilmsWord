package com.example.demo.service;

import com.example.demo.domain.MoviesEntity;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.Optional;


@Service
public class CronService {

    private final MoviesService moviesService;

    public CronService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    // sekunda, minuta, godzina, dzień, miesiąc, dzień tygodnia
    // @Scheduled(cron = "0 * * * * *")   // odpala co 1 minutę
    // @Scheduled(fixedDelay = 15000)  // odpala co 15 sekund
    public void sendRandomMovie() throws MessagingException {
        long movieCount = moviesService.getMovieCount();
        if (movieCount <= 0) {
            System.out.println("Nie ma żadnych filmów w bazie danych!!");
            return;
        }

        long randomMovieId = RandomUtils.nextLong(1, movieCount);
        Optional<MoviesEntity> optionalMovie = moviesService.findById(randomMovieId);
        if (optionalMovie.isEmpty()) {
            System.out.println("Wyliczono losowy ID, ale nie znaleziono w bazie!");
            return;
        }

        MoviesEntity movie = optionalMovie.get();
        moviesService.sendMovie(movie);
    }
}
