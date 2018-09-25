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

    public List<Book> list(){
        return bookRepository.list();
    }

    public List<Book> borrowedList(){
        return bookRepository.borrowedList();
    }

    public List<Book> availableList(){
        return bookRepository.availableList();
    }

    public List<Book> searchByWord (String word){
        return bookRepository.searchByWord(word);
    }

    public List<Book> searchBorrowedByWord (String word){
        return bookRepository.searchBorrowedByWord(word);
    }

    public List<Book> searchAvailableByWord (String word){
        return bookRepository.searchAvailableByWord(word);
    }

    public void add (Book book){
        bookRepository.add(book);
    }

    public void update(Book book) {bookRepository.update(book);}

    public Book searchById(Integer bookId) {return bookRepository.searchById(bookId);}
}
