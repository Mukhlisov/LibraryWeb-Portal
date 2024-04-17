package webportal.libweb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookRestController {

    private final BookService service;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return service.findAllBook();
    }

    @GetMapping("/random")
    public List<Book> getRandomBooks() {
        return service.getRandomBooks();
    }

    @GetMapping("/id/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/title/{title}")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody Book book) {
        service.saveBook(book);
        return "Success! User has been saved";
    }
    
    @Transactional
    @PutMapping("/update")
    public String updateBook(@RequestBody Book book) {
        service.updateBook(book);
        return "Success! User has been updated";
    }
    
    @DeleteMapping("/delete-id/{id}")
    public String deleteBookById(@PathVariable Long id){
        service.deleteBook(id);
        return "Success! User was deleted";
    }
}
