package com.github.mukhlisov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReminderDto {
    private String firstName;
    private String lastName;
    private Long chatId;
    private String email;
    private String bookName;
    private String reminderDate;
}
