package com.github.mukhlisov.controller;

import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.dto.AcceptOrderDto;
import com.github.mukhlisov.dto.DeleteOrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lib-orders")
@RequiredArgsConstructor
public class OrderEditController {
    private static final int PAGE_SIZE = 6;
    private static final int PAGES_IN_ROW = 4;

    private final OrderService orderService;

    @GetMapping({"", "/"})
    public String viewOrders() {
        return "redirect:/lib-orders/page/1";
    }

    @GetMapping("/page/{page_no}")
    public String viewPaginated(@PathVariable(name="page_no") int page_no, Model model) {
        if (page_no < 1){
            return "redirect:/lib-orders/page/1";
        }

        Page<Order> page = orderService.findPaginated(page_no, PAGE_SIZE);
        model.addAttribute("pageable", true);
        model.addAttribute("pagesAmount", PAGES_IN_ROW);
        model.addAttribute("currPage", page_no);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("orders", page.getContent());

        return "order/order_manager";
    }


    @PostMapping("/search")
    public String searchForAuthors(@RequestParam(name = "phoneNumber") String phone, Model model){
        model.addAttribute("orders", orderService.findOrdersByPhoneNumber(phone));
        model.addAttribute("pageable", false);
        return "order/order_manager";
    }

    @GetMapping("/accept")
    public String viewAcceptOrderPage(@RequestParam(name = "order_id") Long id, Model model) {
        AcceptOrderDto order = orderService.findByIdToAccept(id);
        model.addAttribute("order", order);
        return "order/order_accept";
    }

    @PostMapping("/accept")
    public String acceptOrder(@Valid @ModelAttribute AcceptOrderDto acceptOrderDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage).toList());
            model.addAttribute("order", acceptOrderDto);
            return "order/order_accept";
        }
        orderService.acceptOrder(acceptOrderDto);
        return "redirect:/lib-orders/page/1";
    }

    @PostMapping("/close")
    public String closeOrder(@ModelAttribute DeleteOrderDto deleteOrderDto, RedirectAttributes redirect) {
        orderService.deleteOrder(deleteOrderDto);
        redirect.addFlashAttribute("message", "Заказ закрыт");
        return "redirect:/lib-orders/page/1";
    }

}
