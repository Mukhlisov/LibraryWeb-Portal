package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.User;
import com.github.mukhlisov.UserService;
import com.github.mukhlisov.repository.UserRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repository;

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);           
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void deleteByPhoneNumber(String phone){
        repository.deleteByPhoneNumber(phone);
    }

    @Override
    public boolean existsByPhoneNumber(String phone) {
        return repository.existsByPhoneNumber(phone);
    }

}
