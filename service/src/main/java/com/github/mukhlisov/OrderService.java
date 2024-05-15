package com.github.mukhlisov;

import com.github.mukhlisov.dto.OrderCreateDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllOrders();
    Optional<Order> findById(Long id);
    Order findByUserId(Long id);
    Order findByBookId(Long id);
    void saveOrder(OrderCreateDto orderCreateDto);
    void updateOrder(Order issuance);
    void deleteOrderById(Long id);

}
