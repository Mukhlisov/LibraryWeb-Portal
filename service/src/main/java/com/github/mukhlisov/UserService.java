package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.dto.UserRegDto;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
    User saveUser(UserRegDto userDto);
    void updateUser(User user);
    void deleteById(Long id);
    void deleteByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
