package webportal.libweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import webportal.libweb.DTOs.UserRegDTO;
import webportal.libweb.User.UserService;
import webportal.libweb.enums.Role;

@Controller
@AllArgsConstructor
public class UserRegController {

    private final UserService service;

    @GetMapping("/reg")
    public String showRegFrom(Model model){
        model.addAttribute("user", new UserRegDTO());
        return "registration";
    }

    @PostMapping("reg")
    public String registerUserAcc(@ModelAttribute UserRegDTO user){
        if (service.existsByPhoneNumber(user.getPhoneNumber())){
            return "redirect:/reg?fail_pn";
        }
        user.setRole(Role.OWNER);
        service.saveUser(user);
        return "redirect:/reg?success";
    }
    
}
