package com.example.demo.service;


import com.example.demo.configuration.GeneralConfiguration;
import com.example.demo.domain.Countries;
import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.repository.PersonsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonsService {
    private final PersonsRepository personsRepository;

    private final GeneralConfiguration generalConfiguration;
    private final EmailService emailService;

    public PersonsService(PersonsRepository personsRepository,
                          GeneralConfiguration generalConfiguration, EmailService emailService) {
        this.personsRepository = personsRepository;
        this.generalConfiguration = generalConfiguration;
        this.emailService = emailService;
    }

    public Persons createPersons(PersonsDto personsDto, Countries countries) {
        Persons persons = new Persons();
        persons.setFirstName(personsDto.getFirstName());
        persons.setLastName(personsDto.getLasName());
        persons.setCountries(countries);
        return savePersons(persons);
    }

    public Optional<Persons> findByIdPersons(Long id) {
        return personsRepository.findById(id);
    }

    public Persons savePersons(Persons persons) {
        return personsRepository.save(persons);
    }

    public void savePersonPicture(Persons persons, MultipartFile picture) throws IOException {
        String savePath = generalConfiguration.getUploadPath();
        String saveFilename = RandomStringUtils.randomAlphanumeric(15) + ".jpg";
        String saveFullPath = savePath + "/" + saveFilename;
        FileOutputStream fileOutputStream = new FileOutputStream(saveFullPath);
        fileOutputStream.write(picture.getBytes());

        persons.setPicturePath(saveFullPath);
        savePersons(persons);
    }

    public void sendPhotoFilms(Persons persons) throws MessagingException {
        String photoPath = persons.getPicturePath();
        File file = new File(photoPath);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException("Nie można wysłać jako załącznika, podanego pliku: " + photoPath);
        }

        Map<String, File> attachment = Map.of(file.getName(), file);
        String content = "Zdjęcie w załączniku!";
        emailService.sendEmail(persons.getEMail(), "Zdjęcie do filmu", content, attachment);
    }
}