package com.github.mukhlisov.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AcceptOrderDto {
    private String title;
    private Long order_id;
    @NotNull(message = "Заполните поле с датой")
    @FutureOrPresent(message = "Дата не может быть меньше текущей")
    private LocalDate rent_end_date;
}
