package com.github.mukhlisov.dto;

import com.github.mukhlisov.customAnnotations.YearNotInFuture;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Year;

@Getter
@Setter
public class BookDto {

    private Long id;
    @NotEmpty(message = "Поле: Название не может быть пустым")
    private String title;

    @Min(value = 1600, message = "Год не может быть меньше 1600")
    @YearNotInFuture
    private int year;

    @Min(value = 0, message = "Минимальное значение колличества 0")
    private int quantity;
    private String authors;
    private String cover;

    public String getAllAuthors(){
        return authors;
    }

}


