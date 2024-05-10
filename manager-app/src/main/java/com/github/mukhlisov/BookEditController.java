package com.github.mukhlisov;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.mukhlisov.dto.BookDto;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/lib-books")
public class BookEditController {

    private static final int PAGE_SIZE = 6;
    private static final int PAGES_IN_ROW = 4;

    private static final String noCover = "noCover.jpg";

    private final BookService bookService;
    private final StorageService storageService;

    private static boolean isValidFile(String filename){
        return filename.substring(filename.lastIndexOf('.') + 1).matches("jpg|jpeg|png");
    }

    private static String getUniqueFileName(Long id, String filename){
        return id + filename.substring(filename.lastIndexOf('.'));
    }

    @GetMapping
    public String viewAllBooks(){
        return "redirect:/lib-books/page/1";
    }

    @GetMapping("/page/{page_no}")
    public String viewPaginated(@PathVariable(name="page_no") int page_no, Model model) {
        if (page_no < 1){
            return "redirect:/lib-books/page/1";
        }

        Page<Book> page = bookService.findPaginated(page_no, PAGE_SIZE);
        model.addAttribute("pageable", true);
        model.addAttribute("pagesAmount", PAGES_IN_ROW);
        model.addAttribute("currPage", page_no);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listBook", page.getContent());

        return "book_manager";
    }

    @PostMapping("/search")
    public String searchForBooks(@RequestParam(name = "phrase") String phrase, Model model){
        model.addAttribute("listBook", bookService.findByTitle(phrase));
        model.addAttribute("pageable", false);
        return "book_manager";
    }

    @GetMapping("/add")
    public String addBookForm(){
        return "add&update/add_book";
    }

    @PostMapping("/add")
    public String addNewBook(@ModelAttribute BookDto bookDto,
                                @RequestParam(name = "file") MultipartFile file,
                                RedirectAttributes redirect) {
        
        
        bookDto.setCover(noCover);
        Book book = bookService.saveBook(bookDto);
        if (!file.getOriginalFilename().isEmpty()){
            if (isValidFile(file.getOriginalFilename())){
                String uniqueFilename = getUniqueFileName(book.getId(), file.getOriginalFilename());
                storageService.upload(file, uniqueFilename);
                book.setCover(uniqueFilename);
            } else{
                redirect.addFlashAttribute("message", "Ошибка: неверный тип файла!");
                return "redirect:/lib-books/add";
            }
        }
        bookService.updateBook(book);
        redirect.addFlashAttribute("message", "Книга успешно добавлена!");
        return "redirect:/lib-books/page/1";
    }

    @GetMapping("/update")
    public String updateBookForm(@RequestParam(name="id", defaultValue = "-1") Long id, Model model) throws NoSuchElementException{
        Optional<Book> book = bookService.findById(id);
        model.addAttribute("book", book.get());
        return "add&update/update_book";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookDto bookDto,
                        @RequestParam("cover") String currCover,
                        @RequestParam(name = "file") MultipartFile file,
                        RedirectAttributes redirect){

        if (!file.getOriginalFilename().isEmpty()){
            if (isValidFile(file.getOriginalFilename())){
                storageService.upload(file, getUniqueFileName(bookDto.getId(), file.getOriginalFilename()));
            } else{
                redirect.addFlashAttribute("message", "Ошибка: неверный тип файла!");
                return "redirect:/lib-books/update?id=%d".formatted(bookDto.getId());
            }
        }
        bookService.updateBook(bookDto);
        redirect.addFlashAttribute("message", "Книга успешно обновлена!");
        return "redirect:/lib-books/page/1";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam(name = "id") Long id,
                                @RequestParam(name = "cover") String cover,
                                RedirectAttributes redirect){

        if (!cover.equals(noCover)){
            storageService.deleteByName(cover);
        }
        bookService.deleteBook(id);
        redirect.addFlashAttribute("message", "Книга была успешно удалена!");

        return "redirect:/lib-books";
    }

}
