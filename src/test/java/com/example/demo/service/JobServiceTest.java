package com.example.demo.service;

import com.example.demo.domain.Jobs;
import com.example.demo.repository.JobsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {
    private JobsRepository jobsRepository = Mockito.mock(JobsRepository.class);


    @Test
    void shouldfindGenreId() {
        JobService jobService = new JobService(jobsRepository);
        Jobs jobs = new Jobs();
        jobs.setId(1L);
        jobs.setName("Actor");

        //When and Then
        Mockito.when(jobsRepository.findById(1L)).thenReturn(Optional.of(jobs));
    }
}