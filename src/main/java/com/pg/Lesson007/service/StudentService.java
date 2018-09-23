package com.pg.Lesson007.service;

import com.pg.Lesson007.model.Student;
import com.pg.Lesson007.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public List<Student> studentSearch(String name) {
        return studentRepository.studentSearch(name);
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public Student studentById(Integer studentId) {
        return studentRepository.studentById(studentId);
    }

    public void deleteStudent(Student student) {
        Student foundStudent = studentRepository.studentById(student.getId());
        studentRepository.deleteStudent(foundStudent);
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteStudentById(studentId);
    }

}
