package com.example.demo.service;


import com.example.demo.domain.Countries;
import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.repository.PersonsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonsService {
    private final PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public Persons createPersons(PersonsDto personsDto, Countries countries) {
        Persons persons = new Persons();
        persons.setFirstName(personsDto.getFirstName());
        persons.setLastName(personsDto.getLasName());
        persons.setCountries(countries);
        return savePersons(persons);
    }
    public Optional<Persons> findByIdPersons(Long id) {
            return personsRepository.findById(id);}

    public Persons savePersons(Persons persons) {
        return personsRepository.save(persons);
    }
}
