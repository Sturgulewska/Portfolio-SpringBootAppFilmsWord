package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.domain.dto.LoginUserDto;
import com.example.demo.domain.dto.NewUserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody NewUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }

        if (!userDto.getPassword().equals(userDto.getPasswordRepeat())) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setMessage("Hasła sa źle powtórzone");
            errorDto.setField("password");
            return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }

        var createUser = userService.createUser(userDto);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/testLogin", method = RequestMethod.POST)
    public ResponseEntity<Object> testLogin(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }

        String result = "";
        HttpStatus statusResult = null;
        var login = userService.testLogin(loginUserDto);
        if (login) {
            result = "Konto zostało zalogowane!";
            statusResult = HttpStatus.OK;
        } else {
            result ="Nie znaleziono konta o podanych parametrach";
            statusResult = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result, statusResult);
    }
}
