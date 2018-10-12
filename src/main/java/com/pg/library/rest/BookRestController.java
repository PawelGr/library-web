package com.pg.library.rest;

import com.pg.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/books")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> list() {
        return bookService.list()
                .stream()
                .map(BookDto::fromBook)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable("id") Integer id) {
        return BookDto.fromBook(bookService.searchById(id));
    }

    @PostMapping
    public void add(@RequestBody BookDto book) {
        bookService.save(BookDto.fromDto(book));
    }

    @PutMapping
    public void edit(@RequestBody BookDto book) {
        bookService.save(BookDto.fromDto(book));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
    }
}
