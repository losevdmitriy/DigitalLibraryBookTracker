package org.losev.LibraryBookTracker.controllers;

import org.losev.LibraryBookTracker.models.Book;
import org.losev.LibraryBookTracker.services.BooksService;
import org.losev.LibraryBookTracker.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/library/books")
public class BookController {
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BookController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam(value = "sort_by_year", required = false) boolean sort) {

        if ((page != null) && (books_per_page != null)) {
            model.addAttribute("books", booksService.findAll(PageRequest.of(page, books_per_page)));
        } else if (sort) {
            model.addAttribute("books", booksService.findAll(Sort.by("year")));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("person", peopleService.findByBooks(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.update(id, book);
        return "redirect:/library/books";
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/library/books";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable("id") int id) {
        booksService.returnBook(id);
        return "redirect:/library/books/" + id;
    }

    @PostMapping("/issue/{id}")
    public String issueBook(@ModelAttribute("person") int personId, @PathVariable("id") int id) {
        booksService.issueBook(personId, id);
        return "redirect:/library/books/" + id;
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query != null) {
            model.addAttribute("searchResults", booksService.search(query));
        }
        return "books/search";
    }
}
