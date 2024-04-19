package webportal.libweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
//import webportal.libweb.Author.AuthorService;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;
import webportal.libweb.DTOs.BookAddDTO;
import webportal.libweb.Storage.StorageService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/lib/books")
public class BookEditorController {

    private final BookService bookService;
    private final StorageService storageService;

    //private final AuthorService authorService;

    @GetMapping("/add")
    public String addBookForm(){
        return "addBook";
    }

    @PostMapping("/add")
    public String postMethodName(@ModelAttribute BookAddDTO bookAddDTO) {
        bookService.saveBook(new Book(  bookAddDTO.getTitle(), 
                                        bookAddDTO.getQuantity(), 
                                        bookAddDTO.getYear(), 
                                        "cover-name", 
                                        bookAddDTO.getAuthors())
                            );
        return "redirect:/";
    }
    

}
