package com.github.mukhlisov.repository;

import com.github.mukhlisov.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    Order findByUserId(Long id);
    Order findByBookId(Long id);

    @Modifying
    @Query(value = "INSERT INTO Orders (id, book_id, user_id, rent_start_time) values ()", nativeQuery = true)
    Order saveOrder(Long book_id, Long user_id, String rent_start_date);
}
