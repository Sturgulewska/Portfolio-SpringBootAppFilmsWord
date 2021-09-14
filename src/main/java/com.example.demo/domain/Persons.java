package com.example.demo.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "persons")
public class Persons {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "country_id")
    private Countries countries;

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "e_mail")
    private String eMail;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name="persons_movies", joinColumns = {@JoinColumn(name="person_id")},
            inverseJoinColumns = {@JoinColumn(name="job_id")})
    private List<Jobs> jobsList;
}

