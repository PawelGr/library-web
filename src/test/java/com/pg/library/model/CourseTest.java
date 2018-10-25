package com.pg.library.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CourseTest {

    private Course course = new Course();

    @Test
    public void shouldSetId() throws Exception {
        course.setId(5);
        assertEquals(5L, course.getId().longValue());
    }

    @Test
    public void shouldSetName() throws Exception {
        course.setName("Java. Podstawy.");
        assertEquals("Java. Podstawy.", course.getName());
    }

    @Test
    public void shouldSetUser() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        users.add(user1);
        users.add(user2);
        course.setUser(users);
        assertEquals(users, course.getUser());
    }
}
