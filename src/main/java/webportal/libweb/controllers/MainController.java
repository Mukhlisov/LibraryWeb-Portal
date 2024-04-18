package webportal.libweb.controllers;


import lombok.AllArgsConstructor;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller("/")
@AllArgsConstructor
public class MainController {

    private final BookService bookService;

    @GetMapping
    public String homePage(@RequestParam(name = "title", required = false, defaultValue = "") String title, Model model) {
        List<Book> bookList;
        if (title.isEmpty()){
            bookList = bookService.getRandomBooks();
        } else {
            bookList = bookService.findByTitle(title);
        }
        model.addAttribute("bookList", bookList);
        return "home";
    }

    @PostMapping
    public String home_searchingBooks(@RequestParam(name = "title", required = false, defaultValue = "") String title) {
        return "redirect:/?title="+title;
    }

    @GetMapping("/book/")
    public String viewBookPage(@RequestParam(name="id", required = true, defaultValue = "-1") Long id, Model model) {
        Optional<Book> entity = bookService.findById(id);
        if (entity.isEmpty()){
            return "noContent";
        } else{
            model.addAttribute("entity", entity.get());
            return "book";
        }
    }
    
}
