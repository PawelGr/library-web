package com.pg.library.service;

import com.pg.library.model.Book;
import com.pg.library.model.User;
import com.pg.library.repository.BookRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Rule
    public ExpectedException exc = ExpectedException.none();

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    public void shouldList() throws Exception {

        List<Book> list = new ArrayList<Book>();
        list.add(new Book());
        list.add(new Book());
        list.add(new Book());

        when(repository.list()).thenReturn(list);

        List<Book> serviceList = service.list();
        assertEquals(3, serviceList.size());
    }

    @Test
    public void shouldBorrowedList() throws Exception {

        List<Book> list = new ArrayList<Book>();
        list.add(new Book());
        list.add(new Book());

        when(repository.borrowedList()).thenReturn(list);

        List<Book> serviceList = service.borrowedList();
        assertEquals(2, serviceList.size());
    }

    @Test
    public void shouldReturnAvailableList() throws Exception {

        List<Book> list = new ArrayList<Book>();
        list.add(new Book());

        when(repository.availableList()).thenReturn(list);

        List<Book> serviceList = service.availableList();
        assertEquals(1, serviceList.size());
    }

    @Test
    public void shouldSearchByWord() throws Exception {

        List<Book> list = new ArrayList<Book>();
        Book book1 = new Book();
        book1.setTitle("Java. Podstawy. Wydanie X.");
        Book book2 = new Book();
        book2.setTitle("Java. Zaawansowane kodowanie.");
        list.add(book1);
        list.add(book2);

        when(repository.searchByWord("Java")).thenReturn(list);

        List<Book> serviceList = service.searchByWord("Java");
        assertEquals(2, serviceList.size());
    }

    @Test
    public void shouldSearchBorrowedByWord() throws Exception {

        List<Book> list = new ArrayList<Book>();
        Book book = new Book();
        book.setTitle("Python. Podstawy.");
        list.add(book);

        when(repository.searchBorrowedByWord("Podstawy")).thenReturn(list);

        List<Book> serviceList = service.searchBorrowedByWord("Podstawy");
        assertEquals(1, serviceList.size());
    }

    @Test
    public void shouldSearchAvailableByWord() throws Exception {

        List<Book> list = new ArrayList<Book>();
        Book book1 = new Book();
        book1.setTitle("Java. Podstawy. Wydanie X.");
        book1.setUser(null);
        Book book2 = new Book();
        book2.setTitle("Java. Zaawansowane kodowanie.");
        book2.setUser(null);
        list.add(book1);
        list.add(book2);

        when(repository.searchAvailableByWord("Java")).thenReturn(list);

        List<Book> serviceList = service.searchAvailableByWord("Java");
        assertEquals(2, serviceList.size());

    }

    @Test
    public void shouldUpdateExistingBook () throws Exception {

        Book bookToSave = new Book();
        bookToSave.setId(7);
        bookToSave.setTitle("Programowanie Python");

        repository.update(bookToSave);

        verify(repository, times(1)).update(bookToSave);

    }

    @Test
    public void shouldDeleteBook() {

        Book bookToDelete = new Book();
        bookToDelete.setId(5);
        bookToDelete.setTitle("XYZ");
        bookToDelete.setUser(null);

        when(repository.searchById(5)).thenReturn(bookToDelete);

        service.deleteById(5);

        verify(repository, times(1)).delete(bookToDelete);
    }


    @Test
    public void shouldNotDeleteBorrowedBook() {

        Book bookToDelete = new Book();
        bookToDelete.setId(5);
        bookToDelete.setTitle("XYZ");
        bookToDelete.setUser(new User());

        exc.expect(RuntimeException.class);
        exc.expectMessage("Couldn't delete book with id=" + bookToDelete.getId() + ". Book is borrowed.");

        when(repository.searchById(5)).thenReturn(bookToDelete);

        service.deleteById(5);

        verify(repository, times(0)).delete(bookToDelete);
    }
}