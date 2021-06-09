package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "persons_movies")
public class PersonsMovies {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "person_movies",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movies> listMovieEnti = new ArrayList<Movies>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "person_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Persons> listPersonEnti = new ArrayList<Persons>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "job_id")
    private Jobs jobs;
}
