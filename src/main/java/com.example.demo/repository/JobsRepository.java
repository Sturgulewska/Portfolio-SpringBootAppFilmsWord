package com.example.demo.repository;

import com.example.demo.domain.Jobs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<Jobs, Long> {
}
