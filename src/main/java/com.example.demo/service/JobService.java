package com.example.demo.service;

import com.example.demo.domain.Jobs;
import com.example.demo.repository.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobsRepository jobsRepository;

    public Jobs saveJobs (Jobs jobs){
        return jobsRepository.save(jobs);
    }

    public Jobs createJobs(String name) {
        Jobs jobs = new Jobs();
        jobs.setName(name);
        return saveJobs(jobs);
    }
    public Optional<Jobs> findById(Long id) {
        return jobsRepository.findById(id);
    }

}
