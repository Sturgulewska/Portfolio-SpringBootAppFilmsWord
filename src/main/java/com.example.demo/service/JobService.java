package com.example.demo.service;

import com.example.demo.domain.JobsEntity;
import com.example.demo.repository.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobsRepository jobsRepository;

    public JobsEntity saveJobs (JobsEntity jobsEntity){
        return jobsRepository.save(jobsEntity);
    }

    public JobsEntity createJobs(String name) {
        JobsEntity jobsEntity = new JobsEntity();
        jobsEntity.setName(name);
        return saveJobs(jobsEntity);
    }
    public Optional<JobsEntity> findById(Long id) {
        return jobsRepository.findById(id);
    }

}
