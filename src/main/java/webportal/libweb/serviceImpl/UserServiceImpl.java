package webportal.libweb.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import webportal.libweb.models.User;
import webportal.libweb.repository.UserRepo;
import webportal.libweb.services.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo repository;

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void deleteByPhoneNumber(String phone){
        repository.deleteByPhoneNumber(phone);
    }

}
