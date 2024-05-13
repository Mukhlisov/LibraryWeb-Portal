package com.github.mukhlisov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
