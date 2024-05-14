package com.github.mukhlisov.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDto {

    private Long id;
    @NotEmpty(message = "Поле: Название не может быть пустым")
    private String title;

    @Min(value = 1600, message = "Минимальное значение 1600")
    @Pattern(regexp = "^\\d+$", message = "Поле: год должно содержать только цифры")
    private int year;

    @Min(value = 0, message = "Минимальное значение 0")
    @Pattern(regexp = "^\\d+$", message = "Поле: колличество должно содержать только цифры")
    private int quantity;
    private String authors;
    private String cover;

}


