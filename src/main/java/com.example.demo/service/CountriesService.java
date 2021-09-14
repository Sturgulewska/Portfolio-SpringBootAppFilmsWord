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

    @PostConstruct
    public void postConstructMethod() {
        System.out.println("Jestem wywolany po konstruktorze :)");
    }

    @PreDestroy
    public void beforeDestroyMethod() {
        System.out.println("Jestem wywolany przed zniszczeniem :)");
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
