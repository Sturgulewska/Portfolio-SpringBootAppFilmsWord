package com.example.demo.repository;

import com.example.demo.domain.PersonsMoviesEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonsMoviesRepository extends CrudRepository<PersonsMoviesEntity, Long> {
}
