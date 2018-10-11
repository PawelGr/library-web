package com.pg.library.controller;

import com.pg.library.model.Course;
import com.pg.library.service.CourseService;
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

    @GetMapping("/course/save/form")
    public String form(Model model) {
        model.addAttribute("course", new Course());
        return "course/save/form";
    }

    @PostMapping("/course/save")
    public String save(@Valid Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("course", course);
            return "/course/save/form";
        } else {
            courseService.add(course);
            return "redirect:/course/search/list";
        }
    }


}
