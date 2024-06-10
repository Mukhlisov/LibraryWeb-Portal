package com.github.mukhlisov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailNotification {
    private String receiver;
    private String subject;
    private String body;
}
