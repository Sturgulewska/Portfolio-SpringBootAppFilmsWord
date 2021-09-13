package com.example.demo.repository;

import com.example.demo.domain.CountriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends CrudRepository <CountriesEntity, Long> {
}
