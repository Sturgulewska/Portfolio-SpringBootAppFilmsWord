package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public User createUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        return saveUser(user);
    }
}

