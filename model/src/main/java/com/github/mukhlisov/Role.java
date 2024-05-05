package com.github.mukhlisov;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("USER");

    private final String role;


    @Override
    public String getAuthority() {
        return role;
    }
}
