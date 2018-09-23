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

    @GetMapping("/allstudents")
    public String list(Model model) {
        List<User> listOfUsers = userService.getAllStudents();
        model.addAttribute("allstudents", listOfUsers);
        return "allstudents";
    }

    @GetMapping("/user/add/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "addstudentform";
    }

    @PostMapping("/user/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "addstudentform";
        } else {
            userService.addStudent(user);
            return "redirect:/allstudents";
        }
    }

    @GetMapping("/user/search")
    public String search(@RequestParam("search") String word, Model model) {
        List<User> listOfUsers = userService.studentSearch(word);
        model.addAttribute("allstudents", listOfUsers);
        return "allstudents";
    }

    @PostMapping("/student/delete")
    public String user(@ModelAttribute("student") User user) {
        System.out.println(user);
        userService.deleteStudent(user);
        return "redirect:/allstudents";
    }

    @GetMapping("/user/borrow/book/{studentId}/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("studentId") Integer studentId) {

        User user = userService.studentById(studentId);
        Book book = bookService.bookById(bookId);
        book.setUser(user);
        bookService.updateBook(book);
        return "borrowsuccess";
    }

    @GetMapping("/book/return/{bookId}")
    public String returnBook(@PathVariable("bookId") Integer bookId, Integer studentId) {

        Book book = bookService.bookById(bookId);
        User tempUser = book.getUser();
        tempUser.setBook(null);
        book.setUser(null);
        bookService.updateBook(book);
        userService.updateStudent(tempUser);
        return "returnsuccess";
    }
}
