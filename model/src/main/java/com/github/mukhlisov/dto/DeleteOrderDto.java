package com.github.mukhlisov.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteOrderDto {
    Long book_id;
    Long order_id;
}
