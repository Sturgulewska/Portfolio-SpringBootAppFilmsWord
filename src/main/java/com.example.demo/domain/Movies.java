package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Setter(AccessLevel.PUBLIC)
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "production_year")
    private String productionYear;

    @Setter(AccessLevel.PUBLIC)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "country_id")
    private Countries countries;


}

