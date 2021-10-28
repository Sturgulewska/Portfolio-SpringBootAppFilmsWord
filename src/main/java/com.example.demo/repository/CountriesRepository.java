package com.example.demo.repository;

import com.example.demo.domain.Countries;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends CrudRepository <Countries, Long> {
}
