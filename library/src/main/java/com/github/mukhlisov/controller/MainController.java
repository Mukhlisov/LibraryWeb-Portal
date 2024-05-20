package com.github.mukhlisov.controller;


import com.github.mukhlisov.Book;
import com.github.mukhlisov.BookService;
import com.github.mukhlisov.securityModule.LogInInfo;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final BookService bookService;
    private final LogInInfo logInInfo;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("isLoggedIn", logInInfo.isLoggedIn());
        model.addAttribute("bookList", bookService.getRandomBooks());
        return "index";
    }

    @PostMapping
    public String home_searchingBooks(@RequestParam(name = "title") String phrase, Model model) {
        model.addAttribute("isLoggedIn", logInInfo.isLoggedIn());
        model.addAttribute("bookList", bookService.findByTitle(phrase));
        return "index";
    }


    @GetMapping("/book/{id:\\d+}")
    public String viewBookPage(@PathVariable(name = "id") Long id, Model model)
            throws NoSuchElementException {
        model.addAttribute("isLoggedIn", logInInfo.isLoggedIn());
        Book book = bookService.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Книга c идентификатором %d не найдена".formatted(id)));
        model.addAttribute("book", book);
        return "book";
    }
    
}
