package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genres")
@RequiredArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

}
