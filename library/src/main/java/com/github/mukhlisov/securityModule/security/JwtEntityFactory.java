package com.github.mukhlisov.securityModule.security;

import com.github.mukhlisov.User;

public class JwtEntityFactory {
    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getPhoneNumber(),
                user.getPassword()
        );
    }
}
