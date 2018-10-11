package com.pg.library.service;

import com.pg.library.model.User;
import com.pg.library.repository.UserRepository;
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

    public void save(User user) {
        // 1 opcja -> addressRepository.saveAddress(user.getAddress()); -> zapisywanie adresu
        //        // Address otrzymuje ID z bazy danych
        if (user.getId() == null || user.getId() == 0){
            userRepository.add(user);}
        else{
            userRepository.update(user);
        }
    }

    public User searchById(Integer id) {
        return userRepository.searchById(id);
    }

    public void delete(User user) {
        User foundUser = userRepository.searchById(user.getId());
        if (foundUser.getBook().isEmpty()){
            userRepository.delete(foundUser);
        }
    }

}
