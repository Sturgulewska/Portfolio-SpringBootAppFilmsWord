package com.example.demo.repository;

import com.example.demo.domain.MoviesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends CrudRepository<MoviesEntity, Long> {

}
