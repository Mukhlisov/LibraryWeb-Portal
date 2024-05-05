package com.github.mukhlisov.repository;


import com.github.mukhlisov.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
