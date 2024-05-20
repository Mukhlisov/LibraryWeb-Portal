package com.github.mukhlisov.controller;

import java.util.NoSuchElementException;

import com.github.mukhlisov.Author;
import com.github.mukhlisov.AuthorService;
import com.github.mukhlisov.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/lib-authors")
public class AuthorEditController {
    
    private static final int PAGE_SIZE = 6; 
    private static final int PAGES_IN_ROW = 4;

    private final AuthorService authorService;

    @GetMapping
    public String viewAllAuthors(){
        return "redirect:/lib-authors/page/1";
    }

    @GetMapping("/page/{page_no}")
    public String viewPaginated(@PathVariable(name="page_no") int page_no, Model model) {
        if (page_no < 1){
            return "redirect:/lib-authors/page/1";
        }

        Page<Author> page = authorService.findPaginated(page_no, PAGE_SIZE);
        model.addAttribute("pageable", true);
        model.addAttribute("pagesAmount", PAGES_IN_ROW);
        model.addAttribute("currPage", page_no);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listAuthor", page.getContent());

        return "author/author_manager";
    }
    

    @PostMapping("/search")
    public String searchForAuthors(@RequestParam(name = "phrase") String phrase, Model model){
        model.addAttribute("listAuthor", authorService.findAllAuthors(phrase));
        model.addAttribute("pageable", false);
        return "author/author_manager";
    }

    @GetMapping("/add")
    public String addAuthorForm(){
        return "author/add_author";
    }

    @PostMapping("/add")
    public String addNewAuthor(@ModelAttribute AuthorDto authorDto, RedirectAttributes redirect){
        authorService.saveAuthor(authorDto);
        redirect.addFlashAttribute("message", "Автор был успешо добавлен!");
        return "redirect:/lib-authors/page/1";
    }

    @GetMapping("/update/{id:\\d+}")
    public String updateAuthorForm(@PathVariable(name = "id") Long id, Model model) throws NoSuchElementException{
        Author author = authorService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Книга c идентификатором %d не найдена".formatted(id)));
        model.addAttribute("author", author);
        return "author/update_author";
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute AuthorDto authorDto, RedirectAttributes redirect){
        authorService.updateAuthor(authorDto);

        redirect.addFlashAttribute("message", "Автор был успешно обновлен!");
        return "redirect:/lib-authors/page/1";
    }

    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam(name = "id") Long id, RedirectAttributes redirect){
        authorService.deleteById(id);
        
        redirect.addFlashAttribute("message", "Автор был успешно удален!");
        return "redirect:/lib-authors/page/1";
    }
}
