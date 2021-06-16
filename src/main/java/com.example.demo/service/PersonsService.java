package com.example.demo.service;


import com.example.demo.configuration.GeneralConfiguration;
import com.example.demo.domain.Countries;
import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.PersonsDto;
import com.example.demo.domain.dto.SavePersonPictureDto;
import com.example.demo.repository.PersonsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class PersonsService {
    private final PersonsRepository personsRepository;

    private final GeneralConfiguration generalConfiguration;

    public PersonsService(PersonsRepository personsRepository,
                          GeneralConfiguration generalConfiguration) {
        this.personsRepository = personsRepository;
        this.generalConfiguration = generalConfiguration;
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
}
