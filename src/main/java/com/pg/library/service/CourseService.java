package com.pg.library.service;

import com.pg.library.model.Course;
import com.pg.library.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    CourseRepository courseRepository;

    @Autowired
    public CourseService (CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> list(){
        return courseRepository.list();
    }

    public void add (Course course){
        courseRepository.add(course);
    }
}
