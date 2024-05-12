package com.github.mukhlisov;


import com.github.mukhlisov.auth.JwtResponse;
import com.github.mukhlisov.securityModule.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.mukhlisov.auth.JwtRequest;
import com.github.mukhlisov.dto.RegRequestDto;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/sign-up")
    public String showRegistrationForm(HttpSession session){
        session.removeAttribute("accessToken");
        return "sign_up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute RegRequestDto regRequestDto,
                         BindingResult result, RedirectAttributes redirect, Model model) {

        if (result.hasErrors()){
            model.addAttribute("regRequest", regRequestDto);
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "sign_up";
        }else {
            if (!regRequestDto.getPasswordRep().equals(regRequestDto.getPassword())){
                model.addAttribute("regRequest", regRequestDto);
                model.addAttribute("passwordMismatch", true);
                return "sign_up";
            }
            userService.saveUser(regRequestDto);
            redirect.addFlashAttribute("message", "Вы прошли регистрацию!");
        }
        return "redirect:/auth/sign-in";
    }

    @GetMapping("/sign-in")
    public String showLoginForm(){
        return "sign_in";
    }
    
    @PostMapping
    public String signIn(@ModelAttribute JwtRequest jwtRequest, HttpSession session, RedirectAttributes redirect, Model model){
        JwtResponse jwtResponse = authService.login(jwtRequest);
        session.setAttribute("accessToken", jwtResponse.getAccessToken());
        redirect.addFlashAttribute("message", "Вы вошли в аккаунт!");
        return "redirect:/";
    }
}
