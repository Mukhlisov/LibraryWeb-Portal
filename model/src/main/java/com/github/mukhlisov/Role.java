package com.github.mukhlisov;

import lombok.Getter;

@Getter
public enum Role {
    LIBRARIAN("librarian"),
    OWNER("owner");

    private final String representation;

    Role (String representation){
        this.representation = representation;
    }

}
