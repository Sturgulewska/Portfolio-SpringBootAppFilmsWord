package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@RequiredArgsConstructor
public class MoviesEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Setter(AccessLevel.PUBLIC)
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;

    @Column(name = "production_year")
    private String productionYear;

    @Setter(AccessLevel.PUBLIC)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "country_id")
    private CountriesEntity countries;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "persons_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonsEntity> personList;
}

