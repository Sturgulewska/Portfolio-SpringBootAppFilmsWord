package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PersonsDto {
    private String firstName;
    private String lasName;
    private String picturePath;
    private String eMail;
    private Long countriesId;
}


