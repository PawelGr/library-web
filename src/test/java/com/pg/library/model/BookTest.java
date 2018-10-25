package com.pg.library.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BookTest {

    private Book book = new Book();

    @Test
    public void shouldSetId() throws Exception {
        book.setId(21);
        assertEquals(21L, book.getId().longValue());
    }

    @Test
    public void shouldSetTitle() throws Exception {
        book.setTitle("Java. Podstawy. Wydanie X.");
        assertEquals("Java. Podstawy. Wydanie X.", book.getTitle());
    }

    @Test
    public void shouldSetIsbn() throws Exception {
        book.setIsbn("9788328324800");
        assertEquals("9788328324800", book.getIsbn());
    }

    @Test
    public void shouldSetNumberOfPages() {
        book.setNumberOfPages(428);
        assertEquals(428L, book.getNumberOfPages());
    }

    @Test
    public void shouldSetHardCover() {
        book.setHardCover(true);
        assertEquals(true, book.isHardCover());
    }

    @Test
    public void shouldSetUser() {
        User user = new User();
        book.setUser(user);
        assertEquals(user, book.getUser());
    }

    @Test
    public void shouldSetAuthor() {
        Author author = new Author();
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }
}
