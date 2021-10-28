package com.example.demo.service;

import com.example.demo.configuration.GeneralConfiguration;
import com.example.demo.domain.Countries;
import com.example.demo.domain.Jobs;
import com.example.demo.domain.Persons;
import com.example.demo.repository.PersonsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonsTests {

    private PersonsRepository personsRepository = Mockito.mock(PersonsRepository.class);
    private GeneralConfiguration generalConfiguration = Mockito.mock(GeneralConfiguration.class);
    private EmailService emailService = Mockito.mock(EmailService.class);

    @Test
    void shouldFindPersonsId()
    {
        PersonsService personsService = new PersonsService(personsRepository, generalConfiguration, emailService);

        Countries countries = new Countries();
        countries.setId(1L);
        countries.setName("USA");

        Jobs jobs = new Jobs();
        jobs.setId(1L);
        jobs.setName("Actor");
        List<Jobs> jobsList = new ArrayList<>();

        Persons persons = new Persons();
        persons.setId(1L);
        persons.setFirstName("Natalie");
        persons.setLastName("Portmann");
        persons.setCountries(countries);
        persons.setEMail("natalie@wp.pl");
        persons.setPicturePath("C://Users//HP//Desktop//JAVA - projekty//movie_aplication//upload");
        persons.setJobsList(jobsList);

        Mockito.when(personsRepository.findById(1L)).thenReturn(Optional.of(persons));

    }
}
