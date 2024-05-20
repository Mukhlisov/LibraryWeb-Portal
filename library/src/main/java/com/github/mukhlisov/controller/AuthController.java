package com.github.mukhlisov.controller;


import com.github.mukhlisov.UserService;
import com.github.mukhlisov.auth.JwtResponse;
import com.github.mukhlisov.exceptions.UserAlreadyExistsException;
import com.github.mukhlisov.securityModule.AuthService;
import com.github.mukhlisov.securityModule.LogInInfo;
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
import com.github.mukhlisov.dto.RegRequest;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final LogInInfo logInInfo;

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model){
        if (logInInfo.isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("isLoggedIn", false);
        return "auth/sign_up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute RegRequest regRequest,
                         BindingResult result, RedirectAttributes redirect, Model model) {

        model.addAttribute("passwordMismatch", !regRequest.getPasswordRep().equals(regRequest.getPassword()));
        if (result.hasErrors()){
            model.addAttribute("regRequest", regRequest);
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "auth/sign_up";
        }
        try{
            userService.saveUser(regRequest);
            redirect.addFlashAttribute("message", "Вы прошли регистрацию!");
            return "redirect:/auth/sign-in";
        } catch (UserAlreadyExistsException e){
            model.addAttribute("regRequest", regRequest);
            model.addAttribute("userAlreadyExists", e.getMessage());
            return "auth/sign_up";
        }
    }

    @GetMapping("/sign-in")
    public String showLoginForm(Model model){
        if (logInInfo.isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("isLoggedIn", false);
        return "auth/sign_in";
    }
    
    @PostMapping
    public String signIn(@ModelAttribute JwtRequest jwtRequest, HttpSession session){
        JwtResponse jwtResponse = authService.login(jwtRequest);
        session.setAttribute("accessToken", jwtResponse.getAccessToken());
        return "redirect:/";
    }
}
