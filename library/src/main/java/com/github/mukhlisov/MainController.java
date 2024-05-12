package com.github.mukhlisov;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping()
@AllArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("bookList", bookService.getRandomBooks());
        return "home";
    }
    
    @GetMapping("/search")
    public String searchForABook(@RequestParam(name = "phrase") String phrase, Model model){
        model.addAttribute("bookList", bookService.findByTitle(phrase));
        return "home";
    }
    
    @PostMapping
    public String home_searchingBooks(@RequestParam(name = "title") String phrase, RedirectAttributes attributes) {
        attributes.addAttribute("phrase", phrase);
        return "redirect:/search";
    }


    @GetMapping("/book/{id:\\d+}")
    public String viewBookPage(@PathVariable(name = "id") Long id, Model model) throws NoSuchElementException {
        Book book = bookService.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Книга c идентификатором %d не найдена".formatted(id)));
        model.addAttribute("book", book);
        return "book";
    }
    
}
