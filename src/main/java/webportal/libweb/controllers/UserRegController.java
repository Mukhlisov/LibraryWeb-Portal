package webportal.libweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webportal.libweb.UserRegDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/register")
public class UserRegController {

    @GetMapping
    public String viewRegPage() {
        return "registration";
    }
    
    @PostMapping
    public String regNewUser(@ModelAttribute UserRegDto userDto, RedirectAttributes redirect) {
        redirect.addFlashAttribute("success", "Вы успешно зарегистрированны!");
        return "redirect:/register";
    }
    
}
