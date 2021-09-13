package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jobs")
public class JobsEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;


}
