package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewUserDto {

    @NotBlank(message = "Login nie może być pusty!")
    private String login;

    @NotBlank(message = "Hasło nie może być puste!")
    private String password;

    @NotBlank(message = "Powtórzenie hasła nie może być puste!")
    private String passwordRepeat;
}
