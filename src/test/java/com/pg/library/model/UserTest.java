package com.pg.library.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserTest {

    private User user = new User(); // class under test

    @Test
    public void shouldSetId() throws Exception {
        user.setId(5);
        assertEquals(5L, user.getId().longValue());
    }

    @Test
    public void shouldSetName() throws Exception {
        user.setName("John");
        assertEquals("John", user.getName());
    }

    @Test
    public void setSurname() throws Exception {
    }

    @Test
    public void setEmail() throws Exception {
    }

    @Test
    public void setBook() throws Exception {
    }

    @Test
    public void setCourse() throws Exception {
    }

    @Test
    public void setAddress() throws Exception {
    }

}