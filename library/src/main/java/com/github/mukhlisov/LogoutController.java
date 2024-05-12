package com.github.mukhlisov;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(RedirectAttributes redirect, HttpSession session) {
        session.removeAttribute("accessToken");
        redirect.addFlashAttribute("message", "Вы вышли из аккаунта");
        return "redirect:/";
    }
}
