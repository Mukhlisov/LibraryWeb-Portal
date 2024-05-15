package com.github.mukhlisov.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderCreateDto {
    private Long book_id;
    private Long user_id;
    private LocalDate rent_start_date;
}
