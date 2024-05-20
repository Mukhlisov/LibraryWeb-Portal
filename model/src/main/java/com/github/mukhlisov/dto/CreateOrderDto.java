package com.github.mukhlisov.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateOrderDto {
    private String title;
    private Long book_id;
    private Long user_id;

    @NotNull(message = "Заполните поле с датой")
    @FutureOrPresent(message = "Дата не может быть меньше текущей")
    private LocalDate rent_start_date;
}
