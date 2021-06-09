package com.example.demo.domain.dto;

import com.example.demo.domain.Countries;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MoviesDto {

    private String title;

    private Long genreId;

    private String productionYear;

    private Long countrieId;
}
