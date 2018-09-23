package com.pg.Lesson007.service;

import com.pg.Lesson007.model.User;
import com.pg.Lesson007.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllStudents() {
        return userRepository.getAllStudents();
    }

    public List<User> studentSearch(String name) {
        return userRepository.studentSearch(name);
    }

    public void addStudent(User user) {
        // 1 opcja -> addressRepository.saveAddress(user.getAddress()); -> zapisywanie adresu
        // Address otrzymuje ID z bazy danych
        userRepository.addStudent(user);
    }

    public void updateStudent(User user) {
        userRepository.updateStudent(user);
    }

    public User studentById(Integer studentId) {
        return userRepository.studentById(studentId);
    }

    public void deleteStudent(User user) {
        User foundUser = userRepository.studentById(user.getId());
        userRepository.deleteStudent(foundUser);
    }

    public void deleteStudentById(Integer studentId) {
        userRepository.deleteStudentById(studentId);
    }

}
