package com.example.demo.service;

import com.example.demo.domain.PersonsMovies;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


class UserServiceTests {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void shouldFindUserId(){
        //Given
        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setLogin("login");
        user.setId(1L);
        user.setEmail("anna.sturgulewska@yahoo.pl");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }
}

