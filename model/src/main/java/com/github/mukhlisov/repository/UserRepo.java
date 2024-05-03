package com.github.mukhlisov.repository;


import com.github.mukhlisov.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    User findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
