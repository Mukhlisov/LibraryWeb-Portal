package com.github.mukhlisov.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TelegramNotification {
    private Long chatId;
    private String text;
}
