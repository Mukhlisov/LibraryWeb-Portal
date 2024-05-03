package com.github.mukhlisov;

import java.util.Optional;

import com.github.mukhlisov.dto.AuthorAddDTO;
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

    private final AuthorService authorService;

    @GetMapping
    public String viewAllAuthors(@RequestParam(name = "phrase", required = true, defaultValue = "")String phrase, Model model){
        if (!phrase.isEmpty()){
            model.addAttribute("listAuthor", authorService.findAllAuthors(phrase));
            model.addAttribute("pageable", false);
            return "author_manager";
        }
        return "redirect:/lib-authors/page/1";
    }

    @GetMapping("/page/{page_no}")
    public String viewPaginated(@PathVariable(name="page_no") int page_no, Model model) {
        if (page_no < 1){
            return "no_content";
        }
        int page_size = 6, pages_in_row = 4;

        Page<Author> page = authorService.findPaginated(page_no, page_size);
        model.addAttribute("pageable", true);
        model.addAttribute("pagesAmount", pages_in_row);
        model.addAttribute("currPage", page_no);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listAuthor", page.getContent());

        return "author_manager";
    }
    

    @PostMapping("/search")
    public String searchForAuthors(@RequestParam(name = "phrase") String phrase, RedirectAttributes redirect){
        redirect.addAttribute("phrase", phrase);
        return "redirect:/lib-authors";
    }

    @GetMapping("/add")
    public String addAuthorForm(){
        return "add&update/add_author";
    }

    @PostMapping("/add")
    public String addNewAuthor(@ModelAttribute AuthorAddDTO authorDto, RedirectAttributes redirect){
        Author author = new Author(authorDto.getFullName());
        authorService.saveAuthor(author);
        redirect.addFlashAttribute("message", "Автор был успешо добавлен!");
        return "redirect:/lib-authors/page/1";
    }

    @GetMapping("/update")
    public String updateAuthorForm(@RequestParam(name = "id", defaultValue = "-1") Long id, Model model){
        Optional<Author> author = authorService.findById(id);
        if (author.isEmpty()){
            return "no_content";
        } else {
            model.addAttribute("author", author.get());
            return "add&update/update_author";
        }
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute AuthorAddDTO authorDto, RedirectAttributes redirect){
        Author author = authorService.findById(authorDto.getId()).get();
        author.setFullName(authorDto.getFullName());
        authorService.updateAuthor(author);

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
