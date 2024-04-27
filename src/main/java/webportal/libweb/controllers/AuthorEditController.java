package webportal.libweb.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import webportal.libweb.Author.Author;
import webportal.libweb.Author.AuthorService;
import webportal.libweb.DTOs.AuthorAddDTO;

@Controller
@AllArgsConstructor
@RequestMapping("/lib-authors")
public class AuthorEditController {

    private final AuthorService authorService;

    @GetMapping
    public String viewAllAuthors(Model model){
        model.addAttribute("listAuthor", authorService.findAllAuthors());
        return "author_manager";
    }

    @GetMapping("/add")
    public String addAuthorForm(){
        return "add_author";
    }

    @PostMapping("/add")
    public String addNewAuthor(@ModelAttribute AuthorAddDTO authorDto, RedirectAttributes redirect){
        Author author = new Author(authorDto.getFullName());
        authorService.saveAuthor(author);
        redirect.addFlashAttribute("message", "Author successfylly addded!");
        return "redirect:/lib-authors";
    }

    @GetMapping("/update")
    public String updateAuthorForm(@RequestParam(name = "id", required = true, defaultValue = "-1") Long id, Model model){
        Optional<Author> author = authorService.findById(id);
        if (author.isEmpty()){
            return "no_content";
        } else {
            model.addAttribute("author", author.get());
            return "update_author";
        }
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute AuthorAddDTO authorDto, RedirectAttributes redirect){
        Author author = authorService.findById(authorDto.getId()).get();
        author.setFullName(authorDto.getFullName());
        authorService.updateAuthor(author);

        redirect.addFlashAttribute("message", "Author successfylly updated!");
        return "redirect:/lib-authors";
    }

    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam(name = "id") Long id, RedirectAttributes redirect){
        authorService.deleteById(id);

        redirect.addFlashAttribute("message", "Author successfully deleted!");

        return "redirect:/lib-authors";
    }
}
