package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genres")
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

}
