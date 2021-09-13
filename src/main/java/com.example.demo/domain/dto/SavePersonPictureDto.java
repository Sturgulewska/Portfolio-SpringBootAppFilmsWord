package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@RequiredArgsConstructor
public class SavePersonPictureDto {

    @NotNull(message = "Należy wypełnić id osoby do której zapisać zdjęcie!")
    @Min(value = 0, message = "Podano niepoprawny id osoby!")
    private Long personId;

    @NotNull(message = "Należy wysłać zdjęcie!")
    private MultipartFile file;
}
