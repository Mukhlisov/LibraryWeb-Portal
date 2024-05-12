package com.github.mukhlisov.auth;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
