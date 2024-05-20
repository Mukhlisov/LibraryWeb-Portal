package com.github.mukhlisov.repository;

import com.github.mukhlisov.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    List<Order> findByUserId(Long id);
    Order findByBookId(Long id);

    List<Order> findOrderByUserPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM orders WHERE rent_end_date = :date", nativeQuery = true)
    List<Order> findOrdersByRent_end_date(@Param("date")LocalDate date);

    @Modifying
    @Query(value = "UPDATE orders SET rent_end_date = :date WHERE orders.id = :id", nativeQuery = true)
    int updateOrderRentEndDate(@Param("date") LocalDate date, @Param("id") Long id);

    @Query(value = "SELECT * FROM orders WHERE orders.rent_start_date < :date AND orders.rent_end_date IS NOT NULL", nativeQuery = true)
    List<Order> findExpiredOrders(@Param("date") LocalDate date);
}
