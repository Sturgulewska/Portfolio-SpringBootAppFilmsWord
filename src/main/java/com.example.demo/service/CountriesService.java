package com.example.demo.service;

import com.example.demo.domain.CountriesEntity;
import com.example.demo.repository.CountriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CountriesService {

    private final CountriesRepository countriesRepository;

/*    @PostConstruct
    public void postConstructMethod() {
        System.out.println("Jestem wywolany po konstruktorze :)");
    }

    @PreDestroy
    public void beforeDestroyMethod() {
        System.out.println("Jestem wywolany przed zniszczeniem :)");
    }
*/

    public CountriesEntity createCountries(String name) {
        CountriesEntity countries = new CountriesEntity();
        countries.setName(name);
        return saveCountries(countries);
    }

    public CountriesEntity saveCountries(CountriesEntity countries) {
        return countriesRepository.save(countries);
    }

    public Optional<CountriesEntity> findById(Long id) {
        return countriesRepository.findById(id);
    }
}
