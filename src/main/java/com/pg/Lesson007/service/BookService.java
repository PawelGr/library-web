package com.pg.Lesson007.service;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public List<Book> bookByTitle (String title){
        return bookRepository.bookByTitle(title);
    }

    public void addBook (Book book){
        bookRepository.addBook(book);
    }

    public void updateBook(Book book) {bookRepository.updateBook(book);}

    public Book bookById(Integer bookId) {return bookRepository.bookById(bookId);}
}
