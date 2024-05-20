package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.User;
import com.github.mukhlisov.UserService;
import com.github.mukhlisov.dto.RegRequest;
import com.github.mukhlisov.dto.UserInfoDto;
import com.github.mukhlisov.dto.UserUpdateDto;
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
    private final UserRepo userRepo;


    @Override
    public User saveUser(RegRequest userRegDto) throws UserAlreadyExistsException {

        if (repository.existsByPhoneNumber(userRegDto.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Пользователь с номером телефона: %s уже существует"
                    .formatted(userRegDto.getPhoneNumber()));
        }

        User user = new User(
                userRegDto.getFirstName(),
                userRegDto.getLastName(),
                userRegDto.getPhoneNumber(),
                userRegDto.getEmail(),
                passwordEncoder.encode(userRegDto.getPassword()));

        return repository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(UserUpdateDto userUpdateDto) throws UserAlreadyExistsException {
        User user = repository.findById(userUpdateDto.getId()).get();
        if (!userUpdateDto.getPhoneNumber().equals(user.getPhoneNumber()) && repository.existsByPhoneNumber(userUpdateDto.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Пользователь с номером телефона: %s уже существует"
                    .formatted(userUpdateDto.getPhoneNumber()));
        }
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setEmail(userUpdateDto.getEmail());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        repository.save(user);
    }

    @Transactional
    @Override
    public void setChatId(Long user_id, Long chat_id) {
        userRepo.setChatId(user_id, chat_id);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public UserInfoDto findById(Long id) {
        User user = repository.findById(id).get();
        return new UserInfoDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getChatId(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }

}
