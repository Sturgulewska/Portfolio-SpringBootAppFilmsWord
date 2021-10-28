package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

        @GeneratedValue
        @Id
        private Long id;

        @Column(name = "login")
        private String login;

        @Column(name = "e_mail")
        private String email;

}
