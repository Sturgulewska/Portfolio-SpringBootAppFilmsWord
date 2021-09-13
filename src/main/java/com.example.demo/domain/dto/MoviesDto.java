package com.example.demo.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MoviesDto {

    private String title;

    private Long genreId;

    private String productionYear;

    private Long countrieId;
}
