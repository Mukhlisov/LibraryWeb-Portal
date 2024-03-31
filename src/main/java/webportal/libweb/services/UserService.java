package webportal.libweb.services;

import java.util.List;
import java.util.Optional;

import webportal.libweb.models.User;

public interface UserService {
    List<User> findAllUsers();
    User findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteById(Long id);
    void deleteByPhoneNumber(String phone);
}
