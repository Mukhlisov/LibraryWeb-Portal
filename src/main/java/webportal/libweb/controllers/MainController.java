package webportal.libweb.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import webportal.libweb.services.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@AllArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final BookService service;

    @GetMapping
    public String mainPageViewer(Model model) {
        model.addAttribute("listBooks", service.findAllBook());
        return "mainPage";
    }
    
    
}
