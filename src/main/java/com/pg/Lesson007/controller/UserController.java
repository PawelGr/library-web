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
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private BookService bookService;

    @Autowired
    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("user/search/list")
    public String list(Model model) {
        List<User> listOfUsers = userService.list();
        model.addAttribute("list", listOfUsers);
        return "/user/search/list";
    }

    @GetMapping("/user/add/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "/user/add/form";
    }

    @PostMapping("/user/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/user/add/form";
        } else {
            userService.add(user);
            return "redirect:/user/search/list";
        }
    }

    @GetMapping("/user/search/byword")
    public String search(@RequestParam("search") String word, Model model) {
        List<User> listOfUsers = userService.searchByWord(word);
        model.addAttribute("list", listOfUsers);
        return "user/search/list";
    }

    @PostMapping("/user/delete")
    public String user(@ModelAttribute("user") User user) {
        userService.delete(user);
        return "redirect:/user/search/list";
    }

    @GetMapping("/user/borrow/book/{userId}/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Integer userId) {

        User user = userService.searchById(userId);
        Book book = bookService.searchById(bookId);
        book.setUser(user);
        bookService.update(book);
        return "/action/borrow";
    }
}
