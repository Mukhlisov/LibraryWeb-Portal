package webportal.libweb.controllers;


import lombok.AllArgsConstructor;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller("/")
@AllArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping
    public String homePage(@RequestParam(name = "title", required = false, defaultValue = "") String title, Model model) {
        if (title.equals("")){
            model.addAttribute("bookList", bookService.getRandomBooks());
        } else {
            model.addAttribute("bookList", bookService.findByTitle(title));
        }
        return "home";
    }

    @PostMapping
    public String home_searchingBooks(@RequestParam(name = "title", required = false, defaultValue = "") String title) {
        return "redirect:/?title="+title;
    }
    
    @GetMapping("/book/{id}")
    public String viewBookPage(@PathVariable("id") Long id, Model model) {
        Optional<Book> entity = bookService.findById(id);
        if (entity.isEmpty()){
            return "noContent";
        } else{
            model.addAttribute("entity", entity.get());
            return "book";
        }
    }
    
}
