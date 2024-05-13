package com.github.mukhlisov.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.User;
import com.github.mukhlisov.UserService;
import com.github.mukhlisov.dto.RegRequest;
import com.github.mukhlisov.dto.UserProfileInfo;
import com.github.mukhlisov.exceptions.UserAlreadyExistsException;
import com.github.mukhlisov.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User saveUser(RegRequest userDto) throws UserAlreadyExistsException {

        if (repository.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Пользователь с номером телефона: %s уже существует"
                    .formatted(userDto.getPhoneNumber()));
        }

        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhoneNumber(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));

        return repository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public UserProfileInfo findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return new UserProfileInfo(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }

    @Transactional
    @Override
    public void deleteByPhoneNumber(String phone){
        repository.deleteByPhoneNumber(phone);
    }

    @Override
    public boolean existsByPhoneNumber(String phone) {
        return repository.existsByPhoneNumber(phone);
    }

}
