package com.pg.Lesson007.controller;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.Student;
import com.pg.Lesson007.service.BookService;
import com.pg.Lesson007.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allbooks")
    public String list(Model model) {
        model.addAttribute("allbooks", bookService.getAllBooks());
        return "allbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/add/form")
    public String form(Model model) {
        model.addAttribute("book", new Book());
        return "addbookform";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/book/add")
    public String save(Book book) {
        bookService.addBook(book);
        return "redirect:/allbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/searchByTitle")
    public String searchByTitle(@RequestParam("search") String title, Model model) {
        List<Book> listOfBooks = bookService.bookByTitle(title);
        model.addAttribute("allbooks", listOfBooks);
        return "allbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/searchBorrowedByTitle")
    public String searchBorrowedByTitle(@RequestParam("search") String title, Model model) {
        List<Book> listOfBooks = bookService.bookByTitle(title);
        model.addAttribute("borrowedbooks", listOfBooks);
        return "borrowedbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/searchAvaileableByTitle")
    public String searchAvaileableByTitle(@RequestParam("search") String title, Model model) {
        List<Book> listOfBooks = bookService.bookByTitle(title);
        model.addAttribute("availeablebooks", listOfBooks);
        return "availeablebooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/searchbookById/{bookId}")
    public String searchById(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookService.bookById(bookId);
        model.addAttribute("allbooks", book);
        return "allbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrowedbooks")
    public String borrowedBooks(Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        List<Book> borrowedBooks = new ArrayList<>();
        for (int b = 0; b < listOfBooks.size(); b++) {
            Book temp = listOfBooks.get(b);
            if (temp.getStudent() != null) {
                borrowedBooks.add(temp);
                model.addAttribute("borrowedbooks", borrowedBooks);
            }
        }
        return "borrowedbooks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/availeablebooks/{studentId}")
    public String availeableBooks(@PathVariable("studentId") Integer studentId, Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        List<Book> availeableBooks = new ArrayList<>();
        for (int b = 0; b < listOfBooks.size(); b++) {
            Book temp = listOfBooks.get(b);
            if (temp.getStudent() == null) {
                availeableBooks.add(temp);
                model.addAttribute("availeablebooks", availeableBooks);
            }
        }
        model.addAttribute("studentId", studentId);
        return "availeablebooks";
    }

    // NIE DZIAŁA !!!

    @RequestMapping(method = RequestMethod.GET, value = "/borrowedbooksByStudentId/{studentId}")
    public String borrowedBooksByStudentId(@PathVariable("studentId") Integer studentId, Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        List<Book> borrowedBooks = new ArrayList<>();
        for (int b = 0; b < listOfBooks.size(); b++) {
            Book tempBook = listOfBooks.get(b);
            Student tempStudent = tempBook.getStudent();
            System.out.println(tempBook);
            System.out.println(tempStudent);
            System.out.println(tempStudent.getId());
            if (tempStudent.getId() == studentId) {
                borrowedBooks.add(tempBook);
                model.addAttribute("borrowedbooks", borrowedBooks);
            }
        }
        return "borrowedbooks";
    }

}
