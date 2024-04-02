package webportal.libweb.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import webportal.libweb.dataTransferObj.UserRegDTO;
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

    @Override
    public void saveUser(UserRegDTO user) {
        repository.save(new User(
            user.getFirstName(),
            user.getLastName(),
            user.getPhoneNumber(),
            user.getPassword(),
            user.getRole()
        ));            
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

    @Override
    public boolean existsByPhoneNumber(String phone) {
        return repository.existsByPhoneNumber(phone);
    }

}
