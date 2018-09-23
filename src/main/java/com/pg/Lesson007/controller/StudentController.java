package com.pg.Lesson007.controller;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.Student;
import com.pg.Lesson007.service.BookService;
import com.pg.Lesson007.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;
    private BookService bookService;

    @Autowired
    public StudentController(StudentService studentService, BookService bookService) {
        this.studentService = studentService;
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allstudents")
    public String list(Model model) {
        List<Student> listOfStudents = studentService.getAllStudents();
        model.addAttribute("allstudents", listOfStudents);
        return "allstudents";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/add/form")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        return "addstudentform";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/student/add")
    public String save(@Valid Student student, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "addstudentform";
        } else {
            studentService.addStudent(student);
            return "redirect:/allstudents";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/search")
    public String search(@RequestParam("search") String word, Model model) {
        List<Student> listOfStudents = studentService.studentSearch(word);
        model.addAttribute("allstudents", listOfStudents);
        return "allstudents";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/student/delete")
    public String delete(@ModelAttribute("student") Student student) {
        System.out.println(student);
        studentService.deleteStudent(student);
        return "redirect:/allstudents";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/borrow/book/{studentId}/{bookId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("studentId") Integer studentId) {

        Student student = studentService.studentById(studentId);
        Book book = bookService.bookById(bookId);
        book.setStudent(student);
        bookService.updateBook(book);
        return "borrowsuccess";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/return/{bookId}")
    public String returnBook(@PathVariable("bookId") Integer bookId, Integer studentId) {

        Book book = bookService.bookById(bookId);
        Student tempStudent = book.getStudent();
        tempStudent.setBook(null);
        book.setStudent(null);
        bookService.updateBook(book);
        studentService.updateStudent(tempStudent);
        return "returnsuccess";
    }
}
