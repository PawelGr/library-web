package com.pg.library.controller;

import com.pg.library.model.Book;
import com.pg.library.model.Course;
import com.pg.library.model.User;
import com.pg.library.service.BookService;
import com.pg.library.service.CourseService;
import com.pg.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    private UserService userService;
    private BookService bookService;
    private CourseService courseService;

    @Autowired
    public UserController(UserService userService, BookService bookService, CourseService courseService) {
        this.userService = userService;
        this.bookService = bookService;
        this.courseService = courseService;
    }

    @GetMapping("/search/list")
    public String list(Model model) {
        List<User> listOfUsers = userService.list();
        model.addAttribute("list", listOfUsers);
        return "/user/search/list";
    }

    @GetMapping("/add/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "/user/add/form";
    }

    @GetMapping("/edit/{userId}")
    public String form(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("user", userService.searchById(userId));
        return "/user/add/form";
    }

    @PostMapping("/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/user/add/form";
        } else {
            userService.save(user);
            return "redirect:/user/search/list";
        }
    }

    @GetMapping("/search/byword")
    public String search(@RequestParam("search") String word, Model model) {
        List<User> listOfUsers = userService.searchByWord(word);
        model.addAttribute("list", listOfUsers);
        return "user/search/list";
    }

    @PostMapping("/delete")
    public String user(@ModelAttribute("user") User user) {
        if (userService.delete(user) == true){
        return "/action/delete";}
        else{
            return "/action/delete_failed";
        }
    }

    @GetMapping("/borrow/book/{userId}/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Integer userId) {

        User user = userService.searchById(userId);
        Book book = bookService.searchById(bookId);
        book.setUser(user);
        bookService.update(book);
        return "/action/borrow";
    }

    @GetMapping("/select/course/{userId}/{courseId}")
    public String selectCourse(@PathVariable("courseId") Integer courseId, @PathVariable("userId") Integer userId) {

        User user = userService.searchById(userId);
        Course course = courseService.searchById(courseId);
        course.getUser().add(user);
        courseService.update(course);
        return "redirect:/user/search/list";
    }
}
