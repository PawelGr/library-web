package com.pg.library.service;

import com.pg.library.model.Course;
import com.pg.library.repository.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseService service;

    @Test
    public void shouldList() {
        List<Course> list = new ArrayList<>();
        list.add(new Course());
        list.add(new Course());
        list.add(new Course());
        list.add(new Course());

        when(repository.list()).thenReturn(list);

        List<Course> serviceList = service.list();
        assertEquals(4, serviceList.size());

    }

    @Test
    public void shouldSearchById() {

        List<Course> list = new ArrayList<>();
        Course course1 = new Course();
        course1.setId(3);
        Course course2 = new Course();
        course2.setId(4);
        list.add(course1);
        list.add(course2);

        when(repository.searchById(3)).thenReturn(course1);

        List<Course> serviceList = new ArrayList<>();
        serviceList.add(service.searchById(3));

        assertEquals(1,serviceList.size());
    }
}