package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.repository.CountriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CountriesService {

    private final CountriesRepository countriesRepository;

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
