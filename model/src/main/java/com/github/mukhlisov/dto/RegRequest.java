package com.github.mukhlisov.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegRequest {

    @NotEmpty(message = "Поле: Имя не может быть пустым")
    @Pattern(regexp = "^[А-Яа-яЁё]+$", message = "Поле: Имя должно содержать только русские буквы")
    private String firstName;

    @NotEmpty(message = "Поле: Фамилия не может быть пустым")
    @Pattern(regexp = "^[А-Яа-яЁё]+$", message = "Поле: Фамилия должно содержать только русские буквы")
    private String lastName;

    @Size(min = 11, max = 11, message = "Неверный формат номера телефона")
    @Pattern(regexp = "^\\d+$", message = "Номер телефона должен содержать только цифры")
    private String phoneNumber;

    @NotEmpty(message = "Поле: Почта не может быть пустым")
    @Email(message = "Неверный формат почты")
    private String email;

    @Size(min = 5, message = "Пароль слишком короткий")
    private String password;

    private String passwordRep;
}
