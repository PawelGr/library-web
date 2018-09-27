package com.pg.Lesson007.repository;

import com.pg.Lesson007.model.Book;
import com.pg.Lesson007.model.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @PersistenceContext
    EntityManager courseEntityManager;

    public List<Course> list(){
        TypedQuery<Course> query = courseEntityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    public void add(Course course){
        courseEntityManager.persist(course);
    }

}
