package com.pg.Lesson007.repository;

import com.pg.Lesson007.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    EntityManager studentEntityManager;

    public List<User> getAllStudents() {
        TypedQuery<User> query = studentEntityManager.createQuery("SELECT s FROM User s", User.class);
        return query.getResultList();
    }

    public List<User> studentSearch(String word) {
        TypedQuery<User> query = studentEntityManager.createQuery("SELECT s FROM User s WHERE s.name LIKE %:word% OR s.surname LIKE %:word% OR s.course LIKE %:word%", User.class);
        query.setParameter("word", word);
        return query.getResultList();
    }

    public void addStudent(User user) {
        studentEntityManager.persist(user);
    }

    public void updateStudent(User user) {
        studentEntityManager.merge(user);
    }

    public User studentById(Integer id) {
        return studentEntityManager.find(User.class, id);
    }

    public void deleteStudent(User user) {
        studentEntityManager.remove(user);
    }

    public void deleteStudentById(Integer studentId) {
        studentEntityManager.remove(studentId);
    }
}
