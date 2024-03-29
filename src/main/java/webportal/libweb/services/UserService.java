package webportal.libweb.services;

import java.util.List;

import webportal.libweb.models.User;

public interface UserService {
    List<User> findAllUsers();
    User findByPhoneNumber(String phoneNumber);
    void saveUser(User user);
    void updateUser(User user);
    void deleteById(Long id);
}
