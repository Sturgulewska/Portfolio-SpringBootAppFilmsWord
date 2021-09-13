package com.example.demo.service;


import com.example.demo.configuration.GeneralConfiguration;
import com.example.demo.domain.CountriesEntity;
import com.example.demo.domain.PersonsEntity;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.repository.PersonsRepository;
import com.example.demo.service.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PersonsService {
    private final PersonsRepository personsRepository;

    private final GeneralConfiguration generalConfiguration;
    private final EmailService emailService;


    public PersonsEntity createPersons(PersonsDto personsDto, CountriesEntity countries) {
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setFirstName(personsDto.getFirstName());
        personsEntity.setLastName(personsDto.getLasName());
        personsEntity.setCountries(countries);
        return savePersons(personsEntity);
    }

    public Optional<PersonsEntity> findByIdPersons(Long id) {
        return personsRepository.findById(id);
    }

    public PersonsEntity savePersons(PersonsEntity personsEntity) {
        return personsRepository.save(personsEntity);
    }

    public void savePersonPicture(PersonsEntity personsEntity, MultipartFile picture) throws IOException {
        String savePath = generalConfiguration.getUploadPath();
        String saveFilename = RandomStringUtils.randomAlphanumeric(15) + ".jpg";
        String saveFullPath = savePath + "/" + saveFilename;

        Path tempFilePath = Files.createTempFile("convert-picture", "");
        picture.transferTo(tempFilePath.toFile());

        byte[] imageBytes = ImageUtils.convertImageToResolution(tempFilePath.toFile(), "jpg", 640, 480);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFullPath);
        fileOutputStream.write(imageBytes);

        personsEntity.setPicturePath(saveFullPath);
        savePersons(personsEntity);
    }

    public void sendPhotoFilms(PersonsEntity personsEntity) throws MessagingException {
        String photoPath = personsEntity.getPicturePath();
        File file = new File(photoPath);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException("Nie można wysłać jako załącznika, podanego pliku: " + photoPath);
        }

        Map<String, File> attachment = Map.of(file.getName(), file);
        String content = "Zdjęcie w załączniku!";
        emailService.sendEmail(personsEntity.getEMail(), "Zdjęcie do filmu", content, attachment);
    }
}