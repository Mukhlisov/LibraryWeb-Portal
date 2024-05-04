package com.github.mukhlisov.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String fullName;

    public AuthorDto(String fullaName){
        this.fullName = fullaName;
    }
}

