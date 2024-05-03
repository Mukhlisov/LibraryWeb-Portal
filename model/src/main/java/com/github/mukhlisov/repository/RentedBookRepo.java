package com.github.mukhlisov.repository;

import com.github.mukhlisov.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedBookRepo extends JpaRepository<RentedBook, Long>{
    RentedBook findByUserId(Long id);
    RentedBook findByBookId(Long id);
}
