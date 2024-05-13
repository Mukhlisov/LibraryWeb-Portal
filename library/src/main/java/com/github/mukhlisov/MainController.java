package com.github.mukhlisov;


import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping()
@AllArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        boolean isLoggedIn = principal != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("bookList", bookService.getRandomBooks());
        return "index";
    }

    @PostMapping
    public String home_searchingBooks(@RequestParam(name = "title") String phrase, Model model, Principal principal) {
        boolean isLoggedIn = principal != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("bookList", bookService.findByTitle(phrase));
        return "index";
    }


    @GetMapping("/book/{id:\\d+}")
    public String viewBookPage(@PathVariable(name = "id") Long id, Model model, Principal principal)
            throws NoSuchElementException {
        boolean isLoggedIn = principal != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        Book book = bookService.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Книга c идентификатором %d не найдена".formatted(id)));
        model.addAttribute("book", book);
        return "book";
    }
    
}
