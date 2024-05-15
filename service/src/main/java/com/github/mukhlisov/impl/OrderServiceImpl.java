package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.dto.OrderCreateDto;
import com.github.mukhlisov.repository.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo repository;

    @Override
    public List<Order> findAllOrders() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Order findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Order findByBookId(Long id) {
        return repository.findByBookId(id);
    }

    @Transactional
    @Override
    public void saveOrder(OrderCreateDto orderCreateDto) {
        repository.saveOrder(
                orderCreateDto.getBook_id(),
                orderCreateDto.getUser_id(),
                orderCreateDto.getRent_start_date().toString()
                );
    }

    @Override
    public void updateOrder(Order issuance) {
        repository.save(issuance);
    }

    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

}
