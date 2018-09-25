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

    public List<User> list() {
        return userRepository.list();
    }

    public List<User> searchByWord(String word) {
        return userRepository.searchByWord(word);
    }

    public void add(User user) {
        // 1 opcja -> addressRepository.saveAddress(user.getAddress()); -> zapisywanie adresu
        // Address otrzymuje ID z bazy danych
        userRepository.add(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public User searchById(Integer id) {
        return userRepository.searchById(id);
    }

    public void delete(User user) {
        User foundUser = userRepository.searchById(user.getId());
        userRepository.delete(foundUser);
    }

}
