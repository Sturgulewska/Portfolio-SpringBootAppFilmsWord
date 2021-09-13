package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;




}
