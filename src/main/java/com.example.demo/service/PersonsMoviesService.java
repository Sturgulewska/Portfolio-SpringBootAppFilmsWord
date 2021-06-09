package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.domain.dto.*;
import com.example.demo.repository.JobsRepository;
import com.example.demo.repository.MoviesRepository;
import com.example.demo.repository.PersonsMoviesRepository;
import com.example.demo.repository.PersonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonsMoviesService {

    private final PersonsMoviesRepository personsMoviesRepository;
    private final PersonsRepository personsRepository;
    private final MoviesRepository moviesRepository;
    private final JobsRepository jobsRepository;


    public PersonsMoviesService(PersonsMoviesRepository personsMoviesRepository, EmailService emailService, PersonsRepository personsRepository, MoviesRepository moviesRepository, JobsRepository jobsRepository) {
        this.personsMoviesRepository = personsMoviesRepository;
        this.personsRepository = personsRepository;
        this.moviesRepository = moviesRepository;
        this.jobsRepository = jobsRepository;
    }

    public PersonsMovies savePersonMovies(PersonsMovies personsMovies) {
        return personsMoviesRepository.save(personsMovies);
    }

    public Optional<PersonsMovies> findByIdPersons(Long id) {
        return personsMoviesRepository.findById(id);
    }

    public PersonsMoviesService createPersonsMovies(PersonsMoviesService personsMovies) {
        personsMovies.getPersonsList();
        personsMovies.getMovieDtoList();
        return  personsMovies;

    }
/*
    PersonsMovies personsMovies = new PersonsMovies();
        personsMovies.setListMovieEnti(getMovieDtoList);
    List<Movies> moviesList = movies.getMoviesList();
    List<Persons> personsList = persons.getPersonsDtoList();

        personsMovies.setListMovieEnti(moviesList);
        personsMovies.setListPersonEnti(personsList);
        personsMovies.setJobs(jobs);

        /*
          public OrderInfoDto getOrderInfo(ShopOrder shopOrder) {
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        List<Product> productList = shopOrder.getProductList();
        List<ProductRowDto> productRowDtos = new ArrayList<>();


                orderInfoDto.setProductList(productRowDtos);

         */


    public PersonsDtoList getPersonsList() {
        PersonsDtoList personDto = new PersonsDtoList();
        List<PersonsDto> personsDtoList = personDto.getPersonsDtoList();
        List<Persons> personsList = (List<Persons>) personsRepository.findAll();

        personsDtoList.forEach(p -> {
            PersonsDto personsDto = new PersonsDto(p.getFirstName(), p.getLasName(), p.getCountriesId());
            personsDtoList.add(personsDto);
        });
        return personDto;
    }
    public MovieDtoList getMovieDtoList() {
        MovieDtoList movieDto = new MovieDtoList();
        List<MoviesDto> moviesDtoList = movieDto.getMoviesList();
        List<Movies> moviesList = (List<Movies>) moviesRepository.findAll();

        moviesDtoList.forEach(m -> {
            MoviesDto moviesDto = new MoviesDto(m.getTitle(), m.getGenreId(), m.getProductionYear(), m.getCountrieId());
            moviesDtoList.add(moviesDto);
        });
        return movieDto;

    }
/*
    public JobDtoList getJobList() {
        JobDtoList jobDto = new JobDtoList();
        List<JobDto> jobDtoLists = jobDto.getJobsList();
        List<Jobs> jobsList = (List<Jobs>) jobsRepository.findAll();

        jobDtoLists.forEach(j -> {
            JobDto jobDto1 = new JobDto(j.getName());
            jobDtoLists.add(jobDto1);

        });
        return jobDto;
    }
*/
}
