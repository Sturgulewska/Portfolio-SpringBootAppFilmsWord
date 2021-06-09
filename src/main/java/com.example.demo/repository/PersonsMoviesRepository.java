package com.example.demo.repository;

import com.example.demo.domain.PersonsMovies;
import org.springframework.data.repository.CrudRepository;

public interface PersonsMoviesRepository extends CrudRepository<PersonsMovies, Long> {
}
