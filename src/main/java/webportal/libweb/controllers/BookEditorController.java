package webportal.libweb.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private static final String noCover = "noCover.jpg";

    private final BookService bookService;
    private final StorageService storageService;
    private final AuthorService authorService;

    private static boolean isValidFile(String filename){
        if(filename.isEmpty()){
            return true;
        }
        String extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length());
        if (extension.matches("jpg|jpeg|png")){
            return true;
        }
        return false;
    }

    @GetMapping
    public String viewAllBooks(Model model){
        model.addAttribute("listBook", bookService.findAllBook());
        return "book_manager";
    }

    @GetMapping("/add")
    public String addBookForm(){
        return "add_book";
    }

    @PostMapping("/add")
    public String addNewBook(@ModelAttribute BookAddDTO bookAddDTO,
                                @RequestParam(name = "file") MultipartFile file,
                                RedirectAttributes redirect) {
        
        if (!isValidFile(file.getOriginalFilename())){
            redirect.addFlashAttribute("message", "Only images can be uploaded!");
            return "redirect:/lib/books/add";
        }

        String[] names = bookAddDTO.getAuthors().split(";");
        String name;
        Author author;
        Book book = new Book(bookAddDTO.getTitle(),
                            bookAddDTO.getQuantity(),
                            bookAddDTO.getYear(),
                            file.getOriginalFilename(),
                            new HashSet<>(names.length)
                            );
        book = bookService.saveBook(book);

        for (String s : names) {
            name = s.trim();
            if (name.isEmpty()) {
                continue;
            }
            author = authorService.findByFullName(name);
            if (author != null) {
                author.getBooks().add(book);
            } else {
                author = new Author(name);
                author.getBooks().add(book);
                authorService.saveAuthor(author);
            }
            book.getAuthors().add(author);
        }
        
        if (!file.getOriginalFilename().isEmpty()){
            book.setCover(file.getOriginalFilename());
            storageService.upload(file);
        } else{
            book.setCover(noCover);
        }

        bookService.updateBook(book);
        redirect.addFlashAttribute("message", "Book successfully added!");
        return "redirect:/lib/books";
    }

    @GetMapping("/update")
    public String updateBookForm(@RequestParam(name="id", required = true, defaultValue = "-1") Long id, Model model){
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()){
            return "no_content";
        } else {
            model.addAttribute("book", book.get());
            return "update_book";
        }
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookAddDTO bookAddDTO,
                        @RequestParam(name = "file") MultipartFile file,
                        RedirectAttributes redirect){

        if (!isValidFile(file.getOriginalFilename())){
            redirect.addFlashAttribute("message", "Only images can be uploaded!");
            return "redirect:/lib/books/add";
        }

        String[] names = bookAddDTO.getAuthors().split(";");
        String name;
        Book book = bookService.findById(bookAddDTO.getId()).get();
        Set<Author> toDelete = new HashSet<>(book.getAuthors());
        Author author;


        for (String s : names) {
            name = s.trim();
            if (name.isEmpty()) {
                continue;
            }
            author = authorService.findByFullName(name);
            if (author != null) {
                if (toDelete.contains(author)) {
                    toDelete.remove(author);
                } else {
                    author.getBooks().add(book);
                    book.getAuthors().add(author);
                    authorService.saveAuthor(author);
                }
            } else {
                author = new Author(name);
                author.getBooks().add(book);
                book.getAuthors().add(author);

                authorService.saveAuthor(author);
            }
        }

        for (Author author_toDelete : toDelete) {
            book.getAuthors().remove(author_toDelete);
            authorService.deleteRelationShip(author_toDelete, book);
        }
        
        if (!file.getOriginalFilename().isEmpty()){
            if (!book.getCover().equals(noCover)){
                storageService.deleteByName(book.getCover());
            }
            book.setCover(file.getOriginalFilename());
            storageService.upload(file);
        }
        bookService.updateBook(book);
        
        redirect.addFlashAttribute("message", "Book successfully updated!");
        return "redirect:/lib/books";
    }

}
