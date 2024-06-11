package com.github.mukhlisov.controller;

import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.UserService;
import com.github.mukhlisov.dto.ChatIdDto;
import com.github.mukhlisov.dto.UserInfoDto;
import com.github.mukhlisov.dto.UserUpdateDto;
import com.github.mukhlisov.exceptions.UserAlreadyExistsException;
import com.github.mukhlisov.securityModule.LogInInfo;
import com.github.mukhlisov.securityModule.SecurityService;
import com.github.mukhlisov.securityModule.security.JwtEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final SecurityService securityService;
    private final static boolean IS_LOGGED_IN = true;

    @GetMapping
    public String userProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserInfoDto user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("user", user);
        return "user/profile";
    }
    @GetMapping("/update")
    public String updateProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserInfoDto user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("user", user);
        return "user/profile_update";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute UserUpdateDto updateDto, BindingResult result,
                                Model model, RedirectAttributes redirect, Principal principal) {
        JwtEntity jwtEntity = securityService.convertPrincipal(principal);
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            model.addAttribute("user", updateDto);
            return "user/profile_update";
        }
        try{
            userService.updateUser(updateDto);
            if (!jwtEntity.getUsername().equals(updateDto.getPhoneNumber())) {
                userService.setChatId(jwtEntity.getId(), null);
            }
            redirect.addFlashAttribute("message", "Данные обновлены");
            return "redirect:/profile";
        } catch (UserAlreadyExistsException e){
            model.addAttribute("userAlreadyExists", e.getMessage());
            model.addAttribute("user", updateDto);
            return "user/profile_update";
        }
    }

    @GetMapping("/myorders")
    public String myOrdersPage(Model model, Principal principal) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        List<Order> orders = orderService.findByUserId(jwtPrincipal.getId())
                .stream().filter(order -> order.getRent_end_date() == null).toList();
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @GetMapping("/mybooks")
    public String myBooksPage(Model model, Principal principal) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        List<Order> myBooks = orderService.findByUserId(jwtPrincipal.getId())
                .stream().filter(order -> order.getRent_end_date() != null).toList();
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("myBooks", myBooks);
        return "user/my_books";
    }

    @GetMapping("/setTelegramNotifications")
    public String setTelegramNotificationsPage(Model model) {
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        return "user/set_telegram_notifications";
    }

    @PostMapping("/setTelegramNotifications")
    public String setTelegramNotifications(@Valid @ModelAttribute ChatIdDto chatId, BindingResult result,
                                           RedirectAttributes redirect, Model model, Principal principal){
        if (result.hasErrors()) {
            model.addAttribute("isLoggedIn", IS_LOGGED_IN);
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "user/set_telegram_notifications";
        }

        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        userService.setChatId(jwtPrincipal.getId(), Long.parseLong(chatId.getChatId()));
        redirect.addFlashAttribute("message", "Телеграм уведомления подключены!");
        return "redirect:/profile";
    }

}
