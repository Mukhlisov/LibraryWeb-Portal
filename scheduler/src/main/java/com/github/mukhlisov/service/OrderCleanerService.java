package com.github.mukhlisov.service;

import com.github.mukhlisov.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderCleanerService {

    private final OrderService orderService;

    public void deleteExpiredOrders(){
        orderService.deleteExpiredOrders(LocalDate.now());
    }
}
