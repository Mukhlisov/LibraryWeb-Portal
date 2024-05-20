package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.dto.RegRequest;
import com.github.mukhlisov.dto.UserInfoDto;
import com.github.mukhlisov.dto.UserUpdateDto;

public interface UserService {
    Optional<User> findByPhoneNumber(String phoneNumber);
    UserInfoDto findById(Long id);
    User saveUser(RegRequest userRegDto);
    void updateUser(UserUpdateDto userUpdateDto);
    void setChatId(Long user_id, Long chat_id);
}
