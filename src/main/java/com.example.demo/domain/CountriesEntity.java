package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "countries")
public class CountriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private  String name;

}
