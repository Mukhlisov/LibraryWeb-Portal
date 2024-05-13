package com.github.mukhlisov.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionEntity {
    private int code;
    private String head;
    private String body;
}
