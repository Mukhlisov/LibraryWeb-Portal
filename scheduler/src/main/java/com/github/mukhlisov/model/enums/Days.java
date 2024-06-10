package com.github.mukhlisov.model.enums;

import lombok.Getter;

@Getter
public enum Days {
    TODAY ("сегодня"),
    TOMORROW ("завтра");

    private String title;

    Days(String title) {
        this.title = title;
    }
}
