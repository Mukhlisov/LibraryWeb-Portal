package com.github.mukhlisov.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatIdDto {

    @Pattern(regexp = "^\\d+$", message = "Id чата должен содержать только цифры")
    String chatId;
}
