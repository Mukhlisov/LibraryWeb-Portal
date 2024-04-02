package webportal.libweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import webportal.libweb.dataTransferObj.UserRegDTO;
import webportal.libweb.models.enums.Role;
import webportal.libweb.services.UserService;

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
    public String registerUserAcc(@ModelAttribute(name = "user") UserRegDTO user){
        if (service.existsByPhoneNumber(user.getPhoneNumber())){
            return "redirect:/reg?fail_pn";
        }
        user.setRole(Role.OWNER);
        service.saveUser(user);
        return "redirect:/reg?success";
    }
    
}
