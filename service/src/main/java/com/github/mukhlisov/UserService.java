package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.dto.RegRequest;
import com.github.mukhlisov.dto.UserProfileInfo;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findByPhoneNumber(String phoneNumber);
    UserProfileInfo findById(Long id);
    User saveUser(RegRequest userDto);
    void updateUser(User user);
    void deleteById(Long id);
    void deleteByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
