package com.pg.Lesson007.controller;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.Course;
import com.pg.Lesson007.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController (CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/course/search/list")
    public String list(Model model) {
        model.addAttribute("list", courseService.list());
        return "/course/search/list";
    }

    @GetMapping("/course/add/form")
    public String form(Model model) {
        model.addAttribute("course", new Course());
        return "course/add/form";
    }

    @PostMapping("/course/add")
    public String save(@Valid Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("course", course);
            return "/course/add/form";
        } else {
            courseService.add(course);
            return "redirect:/course/search/list";
        }
    }
}
