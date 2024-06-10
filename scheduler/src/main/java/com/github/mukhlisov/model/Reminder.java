package com.github.mukhlisov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reminder {
    private String firstName;
    private String lastName;
    private Long chatId;
    private String email;
    private String bookName;
    private String reminderDate;
}
