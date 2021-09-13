package com.example.demo.service;

import org.springframework.validation.BindingResult;
import com.example.demo.domain.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;


public class ValidateBindingResult {
    public static List<ErrorDto> validateBindingResult(BindingResult bindingResult) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(e -> {
            errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField()));
        });

        return errorDtoList;
    }
}
