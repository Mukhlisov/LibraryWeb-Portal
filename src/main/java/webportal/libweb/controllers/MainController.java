package webportal.libweb.controllers;


import lombok.AllArgsConstructor;

import webportal.libweb.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@AllArgsConstructor
public class MainController {

    private final UserService service;

    @GetMapping("/main")
    public String mainPageViewer(Model model) {
        model.addAttribute("listUsers", service.findAllUsers());
        return "mainPage";
    }
    
    @GetMapping("/order")
    public String getOrderDescription() {
        return "order";
    }


    
}
