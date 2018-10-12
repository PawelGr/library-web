package com.pg.library.service;

import com.pg.library.model.Book;
import com.pg.library.model.User;
import com.pg.library.repository.BookRepository;
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

    public void save(Book book) {
        if (book.getId() == null || book.getId() == 0){
            bookRepository.add(book);}
        else{
            bookRepository.update(book);
        }
    }

    public boolean delete(Book book) {
        Book foundBook = bookRepository.searchById(book.getId());
        if (foundBook.getUser() == null){
            bookRepository.delete(foundBook);
            return true;
        }
        return false;
    }

    public void deleteById(Integer id) {
        Book book = searchById(id);
        boolean delete = delete(book);
        if (!delete) {
            throw new RuntimeException("Couldn't delete book with id=" + id + ". Book is borrowed.");
        }
    }
}
