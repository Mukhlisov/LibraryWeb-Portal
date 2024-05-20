package com.github.mukhlisov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long chatId;
    private String phoneNumber;
    private String email;
}
