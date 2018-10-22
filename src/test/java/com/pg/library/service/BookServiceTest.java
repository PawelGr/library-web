package com.pg.library.service;

import com.pg.library.model.Book;
import com.pg.library.model.User;
import com.pg.library.repository.BookRepository;
import org.junit.Test;
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
    public void shouldDeleteBook() {

        Book bookToDelete = new Book();
        bookToDelete.setId(5);
        bookToDelete.setTitle("XYZ");
        bookToDelete.setUser(null);

        when(repository.searchById(5)).thenReturn(bookToDelete);

        service.deleteById(5);

        verify(repository, times(1)).delete(bookToDelete);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteBorrowedBook() {

        Book bookToDelete = new Book();
        bookToDelete.setId(5);
        bookToDelete.setTitle("XYZ");
        bookToDelete.setUser(new User());

        when(repository.searchById(5)).thenReturn(bookToDelete);

        service.deleteById(5);

        verify(repository, times(0)).delete(bookToDelete);
    }
}