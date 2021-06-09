package com.example.demo.repository;

import com.example.demo.domain.Persons;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepository extends CrudRepository<Persons, Long> {
}
