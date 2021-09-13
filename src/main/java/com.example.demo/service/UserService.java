package com.example.demo.service;

import com.example.demo.domain.UserEntity;
import com.example.demo.domain.dto.LoginUserDto;
import com.example.demo.domain.dto.NewUserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity createUser(NewUserDto dto) {
        var entity = new UserEntity();
        entity.setLogin(dto.getLogin());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return saveUser(entity);
    }

    public Boolean testLogin(LoginUserDto dto) {
        var user = userRepository.findByLogin(dto.getLogin());
        var result = user.isPresent();
        if (result) {
            result = passwordEncoder.matches(dto.getPassword(), user.get().getPassword());
        }

        return result;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
