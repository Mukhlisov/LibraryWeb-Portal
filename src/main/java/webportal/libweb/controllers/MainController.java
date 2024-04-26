package webportal.libweb.controllers;


import lombok.AllArgsConstructor;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller("/")
@AllArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping
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

    @GetMapping("/book/")
    public String viewBookPage(@RequestParam(name="id", required = true, defaultValue = "-1") Long id, Model model) {
        Optional<Book> wraper = bookService.findById(id);
        if (wraper.isEmpty()){
            return "no_content";
        } else{
            model.addAttribute("entity", wraper.get());
            return "book";
        }
    }
    
}
