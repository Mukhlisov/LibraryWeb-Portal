package webportal.libweb.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import webportal.libweb.services.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@AllArgsConstructor
public class MainController {

    private final BookService service;

    @GetMapping("/main")
    public String mainPageViewer(Model model) {
        model.addAttribute("listBooks", service.findAllBook());
        return "mainPage";
    }
    
    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String loggedIn(){
        return "redirect:/main";
    }

    @GetMapping("/order")
    public String getOrderDescription() {
        return "order";
    }


    
}
