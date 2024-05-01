package webportal.libweb.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    User findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteById(Long id);
    void deleteByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
