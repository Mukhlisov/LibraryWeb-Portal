package webportal.libweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import webportal.libweb.models.User;
import webportal.libweb.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable(name = "phone") String phone) {
        return service.findByPhoneNumber(phone);
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        service.saveUser(user);
        return "Success! User has been saved";
    }
    
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        service.updateUser(user);
        return "Success! User has been updated";
    }
    
    @DeleteMapping("/delete-id/{id}")
    public String deleteUserById(@PathVariable(name = "id") Long id){
        service.deleteById(id);
        return "Success! User was deleted";
    }

    @DeleteMapping("/delete-phone/{phone}")
    public String deleteUserByPhone(@PathVariable(name = "phone") String phone){
        service.deleteByPhoneNumber(phone);
        return "Success! User was deleted";
    }
}
