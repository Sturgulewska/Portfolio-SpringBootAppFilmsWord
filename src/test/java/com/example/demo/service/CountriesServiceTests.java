package com.example.demo.service;

import com.example.demo.domain.Countries;
import com.example.demo.repository.CountriesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class CountriesServiceTests {

    private CountriesRepository countriesRepository = Mockito.mock(CountriesRepository.class);

    @Test
    void shouldFindCountriesId(){
        //Given
        CountriesService countriesService = new CountriesService(countriesRepository);
        Countries countries = new Countries();
        countries.setId(1L);
        countries.setName("Polska");

        //When and Then
        Mockito.when(countriesRepository.findById(1L)).thenReturn(Optional.of(countries));
    }
}
