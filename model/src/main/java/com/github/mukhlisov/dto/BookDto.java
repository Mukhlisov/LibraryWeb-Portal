package com.github.mukhlisov.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private int year;
    private int quantity;
    private String authors;
    private String cover;
}


