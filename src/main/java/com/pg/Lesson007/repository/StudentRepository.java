package com.pg.Lesson007.repository;

import com.pg.Lesson007.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @PersistenceContext
    EntityManager studentEntityManager;

    public List<Student> getAllStudents() {
        TypedQuery<Student> query = studentEntityManager.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    public List<Student> studentSearch(String word) {
        TypedQuery<Student> query = studentEntityManager.createQuery("SELECT s FROM Student s WHERE s.name LIKE %:word% OR s.surname LIKE %:word% OR s.course LIKE %:word%", Student.class);
        query.setParameter("word", word);
        return query.getResultList();
    }

    public void addStudent(Student student) {
        studentEntityManager.persist(student);
    }

    public void updateStudent(Student student) {
        studentEntityManager.merge(student);
    }

    public Student studentById(Integer id) {
        return studentEntityManager.find(Student.class, id);
    }

    public void deleteStudent(Student student) {
        studentEntityManager.remove(student);
    }

    public void deleteStudentById(Integer studentId) {
        studentEntityManager.remove(studentId);
    }
}
