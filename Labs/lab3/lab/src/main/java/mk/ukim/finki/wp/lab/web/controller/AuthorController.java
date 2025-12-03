package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthorsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Author> authors = authorService.listAuthors();

        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography
    ) {
        authorService.create(name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/edit/{authorId}")
    public String editAuthor(@PathVariable Long authorId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography)
    {
        authorService.update(authorId, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("/author-form/{id}")
    public String getEditAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/authors?error=AuthorNotFound";
        }
        model.addAttribute("author", author);
        model.addAttribute("authors", authorService.listAuthors());
        return "author-form";
    }

    @GetMapping("/author-form")
    public String getAddAuthorPage(Model model) {
        model.addAttribute("authors", authorService.listAuthors());
        return "author-form";
    }
}
