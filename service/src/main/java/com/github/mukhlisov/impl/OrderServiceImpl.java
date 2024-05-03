package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.repository.OrderRepo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo repository;

    @Override
    public List<Order> findAllIssuances() {
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

    @Override
    public void saveIssuance(Order issuance) {
        repository.save(issuance);
    }

    @Override
    public void updateIssuance(Order issuance) {
        repository.save(issuance);
    }

    @Override
    public void deleteIssuanceById(Long id) {
        repository.deleteById(id);
    }

}
