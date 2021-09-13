package com.example.demo.repository;

import com.example.demo.domain.JobsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<JobsEntity, Long> {
}
