package com.example.demo.controller;

import com.example.demo.domain.Persons;
import com.example.demo.domain.dto.CreateUserDto;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RequestMapping(value = "/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(
            value = "/createUser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto) {
        var user = userService.createUser(userDto);
        var result = new CreateUserDto();
        result.setUserId(user.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
