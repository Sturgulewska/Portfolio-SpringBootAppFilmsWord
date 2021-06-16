package com.example.demo.service.utils;

import com.example.demo.domain.dto.ErrorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PersonPictureValidator {
    private static final long maxFileSize = 5000000;   // 5 MB
    private static final List<String> acceptTypes = List.of("image/jpeg");

    public ErrorDto validateFile(MultipartFile file) {
        ErrorDto errorDto = new ErrorDto();
        if (file == null || file.isEmpty()) {
            errorDto.setMessage("Należy wysłać plik!");
        } else if (file.getSize() >= maxFileSize) {
            errorDto.setMessage("Wysłano zbyt duży plik! Max 5 MB!");
        } else if (!acceptTypes.contains(file.getContentType())) {
            errorDto.setMessage("Można wysyłać tylko plik jpg!");
        }

        return errorDto;
    }
}
