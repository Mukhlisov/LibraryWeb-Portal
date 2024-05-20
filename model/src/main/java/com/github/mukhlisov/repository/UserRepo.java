package com.github.mukhlisov.repository;


import com.github.mukhlisov.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phone);

    @Modifying
    @Query(value = "UPDATE users SET chat_id = :chat_id WHERE id = :user_id", nativeQuery = true)
    int setChatId(@Param("user_id") Long user_id, @Param("chat_id") Long chatId);
}
