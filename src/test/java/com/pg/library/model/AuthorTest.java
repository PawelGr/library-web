package com.pg.library.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {

    Author author = new Author();

    @Test
    public void shouldSetId() {
        author.setId(7);
        assertEquals(7L, author.getId().longValue());
    }

    @Test
    public void shouldSetName() {
        author.setName("Jay");
        assertEquals("Jay", author.getName());
    }

    @Test
    public void shouldSetSurname() {
        author.setSurname("Horstmann");
        assertEquals("Horstmann", author.getSurname());
    }
}