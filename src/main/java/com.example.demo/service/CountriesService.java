package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.repository.CountriesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountriesService {

    private final CountriesRepository countriesRepository;

    public CountriesService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public Countries createCountries(String name) {
        Countries countries = new Countries();
        countries.setName(name);
        return saveCountries(countries);

    }

    public Countries saveCountries(Countries countries) {
        return countriesRepository.save(countries);
    }

    public Optional<Countries> findById(Long id) {
        return countriesRepository.findById(id);
    }
}
