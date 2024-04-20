package webportal.libweb.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import webportal.libweb.Author.Author;
import webportal.libweb.Author.AuthorService;
import webportal.libweb.Book.Book;
import webportal.libweb.Book.BookService;
import webportal.libweb.DTOs.BookAddDTO;
import webportal.libweb.FileUploader.StorageService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/lib/books")
public class BookEditorController {

    private final BookService bookService;
    private final StorageService storageService;

    private final AuthorService authorService;

    @GetMapping("/add")
    public String addBookForm(){
        return "addBook";
    }

    @PostMapping("/add")
    public String postMethodName(@ModelAttribute BookAddDTO bookAddDTO,
                                @RequestParam(name = "file") MultipartFile file) {
        
        storageService.upload(file);
        String[] names = bookAddDTO.getAuthors().split(";");
        String name;
        Author author;
        Book book = new Book(bookAddDTO.getTitle(),
                            bookAddDTO.getQuantity(),
                            bookAddDTO.getYear(),
                            file.getOriginalFilename(),
                            new ArrayList<>(names.length)
                            );
        book = bookService.saveBook(book);

        for (int i = 0; i < names.length; i++) {
            name = names[i].trim();
            author = authorService.findByFullName(name);
            if (author != null){
                author.getBooks().add(book);
            } else{
                author = new Author(name);
                author.getBooks().add(book);
                authorService.saveAuthor(author);
            }
            book.getAuthors().add(author);
        }
        bookService.updateBook(book);
        
        return "redirect:/";
    }
}
