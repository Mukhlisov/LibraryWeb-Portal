package com.github.mukhlisov.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.Book;
import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.User;
import com.github.mukhlisov.dto.AcceptOrderDto;
import com.github.mukhlisov.dto.DeleteOrderDto;
import com.github.mukhlisov.dto.CreateOrderDto;
import com.github.mukhlisov.exceptions.BookQuantityException;
import com.github.mukhlisov.repository.BookRepo;
import com.github.mukhlisov.repository.OrderRepo;
import com.github.mukhlisov.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    @Override
    public Page<Order> findPaginated(int page, int page_size) {
        Pageable pageable = PageRequest.of(page - 1, page_size);
        return orderRepo.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepo.findByUserId(id);
    }

    @Override
    public List<Order> findByRentEndDate(LocalDate endDate) {
        return orderRepo.findOrdersByRent_end_date(endDate);
    }

    @Transactional
    @Override
    public void saveOrder(CreateOrderDto createOrderDto) throws BookQuantityException {
        Book book = bookRepo.findById(createOrderDto.getBook_id()).get();
        if (book.getQuantity() < 1){
            throw new BookQuantityException("В библиотеке нет экземпляров данной книги: %s".formatted(book.getTitle()));
        }
        User user = userRepo.findById(createOrderDto.getUser_id()).get();
        Order order = new Order(
                book,
                user,
                createOrderDto.getRent_start_date()
        );
        orderRepo.save(order);
        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);
    }

    @Transactional
    @Override
    public void deleteOrder(DeleteOrderDto deleteOrderDto) {
        orderRepo.deleteById(deleteOrderDto.getOrder_id());
        bookRepo.incrementQuantityById(deleteOrderDto.getBook_id());
    }

    @Override
    public List<Order> findOrdersByPhoneNumber(String phoneNumber) {
        return orderRepo.findOrderByUserPhoneNumber(phoneNumber);
    }

    @Override
    public AcceptOrderDto findByIdToAccept(Long id) {
        Order order = orderRepo.findById(id).get();
        return new AcceptOrderDto(
                order.getBook().getTitle(),
                order.getId(),
                order.getRent_end_date()
        );
    }

    @Transactional
    @Override
    public void acceptOrder(AcceptOrderDto acceptOrderDto) {
        orderRepo.updateOrderRentEndDate(acceptOrderDto.getRent_end_date(), acceptOrderDto.getOrder_id());
    }

    @Transactional
    @Override
    public void deleteExpiredOrders(LocalDate date) {
        List<Order> orders = orderRepo.findExpiredOrders(date);
        for (Order order : orders) {
            bookRepo.incrementQuantityById(order.getBook().getId());
            orderRepo.delete(order);
        }
    }

}
