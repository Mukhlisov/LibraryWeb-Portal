package webportal.libweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webportal.libweb.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    User findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phone);
}
