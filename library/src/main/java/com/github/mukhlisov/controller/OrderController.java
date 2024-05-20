package com.github.mukhlisov.controller;

import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.dto.BookMinInfo;
import com.github.mukhlisov.dto.DeleteOrderDto;
import com.github.mukhlisov.dto.CreateOrderDto;
import com.github.mukhlisov.exceptions.BookQuantityException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    @GetMapping("/create")
    public String createOrderPage(@ModelAttribute BookMinInfo bookDto, Model model) {
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("book", bookDto);
        return "order/order_create";
    }

    @PostMapping("/create")
    public String createOrder(@Valid @ModelAttribute CreateOrderDto createOrderDto, BindingResult result,
                              RedirectAttributes redirect, Model model, Principal principal) {

        if (result.hasErrors()) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("book", createOrderDto);
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "order/order_create";
        }
        JwtEntity jwtEntity = securityService.convertPrincipal(principal);
        createOrderDto.setUser_id(jwtEntity.getId());
        try {
            orderService.saveOrder(createOrderDto);
            redirect.addFlashAttribute("message", "Заказ оформлен!");
        } catch (BookQuantityException e) {
            redirect.addFlashAttribute("message", "Ошибка: %s".formatted(e.getMessage()));
        }
        return "redirect:/book/%d".formatted(createOrderDto.getBook_id());
    }

    @PostMapping("/reject")
    public String cancelOrder(@ModelAttribute DeleteOrderDto deleteOrderDto, RedirectAttributes redirect) {
        orderService.deleteOrder(deleteOrderDto);
        redirect.addFlashAttribute("message", "Заказ отменен!");
        return "redirect:/profile/orders";
    }
}
