package com.example.demo.domain;

import com.example.demo.domain.dto.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "persons_movies")
@NoArgsConstructor
@AllArgsConstructor
public class PersonsMovies {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "persons_movies",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Movies movies;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "persons_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Persons persons;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "job_id")
    private Jobs jobs;


}
