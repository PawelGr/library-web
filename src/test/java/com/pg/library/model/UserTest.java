package com.pg.library.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

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
    public void shouldSetSurname() throws Exception {
        user.setSurname("Nowak");
        assertEquals("Nowak", user.getSurname());
    }

    @Test
    public void shouldSetEmail() throws Exception {
        user.setEmail("jn@gmail.com");
        assertEquals("jn@gmail.com", user.getEmail());
    }

    @Test
    public void shouldSetBook() throws Exception {
        ArrayList<Book> books = new ArrayList<>();
        Book book1 = new Book();
        Book book2 = new Book();
        books.add(book1);
        books.add(book2);
        user.setBook(books);
        assertEquals(books,user.getBook());
    }

    @Test
    public void shouldSetCourse() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        Course course2 = new Course();
        courses.add(course1);
        courses.add(course2);
        user.setCourse(courses);
        assertEquals(courses,user.getCourse());
    }

    @Test
    public void shouldSetAddress() throws Exception {
        Address address = new Address();
        user.setAddress(address);
        assertEquals(address,user.getAddress());
    }

}