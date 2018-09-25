package com.pg.Lesson007.controller;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.User;
import com.pg.Lesson007.service.BookService;
import com.pg.Lesson007.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(Book book) {
        bookService.add(book);
        return "redirect:/book/search/list";
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

    // NIE DZIAŁA !!!

    @GetMapping("/borrowedbooksByStudentId/{studentId}")
    public String borrowedBooksByStudentId(@PathVariable("studentId") Integer studentId, Model model) {
        List<Book> listOfBooks = bookService.list();
        List<Book> borrowedBooks = new ArrayList<>();
        for (int b = 0; b < listOfBooks.size(); b++) {
            Book tempBook = listOfBooks.get(b);
            User tempUser = tempBook.getUser();
            System.out.println(tempBook);
            System.out.println(tempUser);
            System.out.println(tempUser.getId());
            if (tempUser.getId() == studentId) {
                borrowedBooks.add(tempBook);
                model.addAttribute("borrowedbooks", borrowedBooks);
            }
        }
        return "borrowedbooks";
    }

    @GetMapping("/book/return/{bookId}")
    public String returnBook(@PathVariable("bookId") Integer bookId) {

        Book book = bookService.searchById(bookId);
        book.setUser(null);
        bookService.update(book);
        return "/action/return";
    }

}
