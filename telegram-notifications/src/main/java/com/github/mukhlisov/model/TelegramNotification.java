package com.github.mukhlisov.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramNotification {
    private Long chatId;
    private String text;

    @JsonCreator
    public TelegramNotification(@JsonProperty("chatId") Long chatId, @JsonProperty("text") String text) {
        this.chatId = chatId;
        this.text = text;
    }
}
