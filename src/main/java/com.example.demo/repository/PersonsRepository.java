package com.example.demo.repository;

import com.example.demo.domain.PersonsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepository extends CrudRepository<PersonsEntity, Long> {
}
