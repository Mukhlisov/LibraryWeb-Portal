package com.github.mukhlisov;

import com.github.mukhlisov.dto.AcceptOrderDto;
import com.github.mukhlisov.dto.DeleteOrderDto;
import com.github.mukhlisov.dto.CreateOrderDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Page<Order> findPaginated(int page, int page_size);
    Optional<Order> findById(Long id);
    List<Order> findByUserId(Long id);
    List<Order> findByRentEndDate(LocalDate endDate);
    void saveOrder(CreateOrderDto createOrderDto);
    void deleteOrder(DeleteOrderDto deleteOrderDto);
    List<Order> findOrdersByPhoneNumber(String phoneNumber);
    AcceptOrderDto findByIdToAccept(Long id);
    void acceptOrder(AcceptOrderDto acceptOrderDto);
    void deleteExpiredOrders(LocalDate date);
}
