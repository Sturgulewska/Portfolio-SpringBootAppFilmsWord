package com.example.demo.Controller;

import com.example.demo.domain.Countries;
import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.service.CountriesService;
import com.example.demo.service.JobService;
import com.example.demo.service.MoviesService;
import com.example.demo.service.PersonsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonsController {

    private final PersonsService personsService;
    private final MoviesService moviesService;
    private final JobService jobService;
    private final CountriesService countriesService;

    public PersonsController(PersonsService personsService, MoviesService moviesService, JobService jobService, CountriesService countriesService) {
        this.personsService = personsService;
        this.moviesService = moviesService;
        this.jobService = jobService;
        this.countriesService = countriesService;
    }

    @RequestMapping(value = "/getFindPersons/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFindPerson(@PathVariable("personId") Long personId) {
        Optional<Persons> personsOptional = personsService.findByIdPersons(personId);
        return new ResponseEntity<>(personsOptional, HttpStatus.OK);
    }

    @RequestMapping(value = "/add_person", method = RequestMethod.POST)
    public ResponseEntity<Object> addPerson(@RequestBody @Valid PersonsDto personsDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }
        Optional<Countries> optionalCountries = countriesService.findById(personsDto.getCountriesId());
        if (optionalCountries.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono kraju", "countries_id "), HttpStatus.BAD_REQUEST);
        }
        personsService.createPersons(personsDto, optionalCountries.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
