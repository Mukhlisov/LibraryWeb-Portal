package com.github.mukhlisov;

import com.github.mukhlisov.dto.BookMinInfo;
import com.github.mukhlisov.dto.OrderCreateDto;
import com.github.mukhlisov.securityModule.SecurityService;
import com.github.mukhlisov.securityModule.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createOrder(@ModelAttribute OrderCreateDto orderCreateDto, RedirectAttributes redirect, Principal principal) {
        JwtEntity jwtEntity = securityService.convertPrincipal(principal);
        orderCreateDto.setUser_id(jwtEntity.getId());
        orderService.saveOrder(orderCreateDto);
        redirect.addFlashAttribute("message", "Заказ оформлен!");
        return "redirect:/book/%d".formatted(orderCreateDto.getBook_id());
    }
}
