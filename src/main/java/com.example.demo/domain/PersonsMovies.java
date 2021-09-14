package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persons_movies")
@NoArgsConstructor
@AllArgsConstructor
public class PersonsMovies {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "job_id")
    private Long jobs;
}
