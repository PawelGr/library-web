package com.pg.Lesson007.controller;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.User;
import com.pg.Lesson007.service.BookService;
import com.pg.Lesson007.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/book/search/list")
    public String list(Model model) {
        model.addAttribute("list", bookService.list());
        return "/book/search/list";
    }

    @GetMapping("/book/add/form")
    public String form(Model model) {
        model.addAttribute("book", new Book());
        return "book/add/form";
    }

    @PostMapping("/book/add")
    public String save(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "/book/add/form";
        } else {
            bookService.add(book);
            return "redirect:/book/search/list";
        }
    }

    @GetMapping("/book/search/byword")
    public String search(@RequestParam("search") String word, Model model) {
        List<Book> listOfBooks = bookService.searchByWord(word);
        model.addAttribute("list", listOfBooks);
        return "/book/search/list";
    }

    @GetMapping("/book/search/borrowed")
    public String borrowedList(Model model) {
        model.addAttribute("list", bookService.borrowedList());
        return "/book/search/borrowed";
    }

    @GetMapping("/book/search/borrowed/byword")
    public String searchBorrowedByTitle(@RequestParam("search") String word, Model model) {
        model.addAttribute("list", bookService.searchBorrowedByWord(word));
        return "/book/search/borrowed";
    }

    @GetMapping("/book/search/available")
    public String availableBooksList(Model model) {
        model.addAttribute("list", bookService.availableList());
        return "/book/search/available";
    }

    @GetMapping("/book/search/available/{userId}")
    public String availableBooks(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("list", bookService.availableList());
        model.addAttribute("userId", userId);
        return "/book/search/available";
    }

    @GetMapping("/book/search/available/byword")
    public String searchAvaileableByTitle(@RequestParam("search") String word, Model model) {
        model.addAttribute("list", bookService.searchAvailableByWord(word));
        return "/book/search/available";
    }

    @GetMapping("/book/return/{bookId}")
    public String returnBook(@PathVariable("bookId") Integer bookId) {

        Book book = bookService.searchById(bookId);
        book.setUser(null);
        bookService.update(book);
        return "/action/return";
    }

}
