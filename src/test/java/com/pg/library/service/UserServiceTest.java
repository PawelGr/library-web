package com.pg.library.service;

import com.pg.library.model.Book;
import com.pg.library.model.User;
import com.pg.library.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldList() {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        list.add(new User());

        when(repository.list()).thenReturn(list);

        List<User> serviceList = service.list();
        assertEquals(4, serviceList.size());

    }

    @Test
    public void shouldSearchByWord() {
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setName("Paweł");
        user1.setSurname("Grabowski");
        User user2 = new User();
        user2.setName("Adam");
        user2.setSurname("Grabowski");
        list.add(user1);
        list.add(user2);


        when(repository.searchByWord("Grabowski")).thenReturn(list);

        List<User> serviceList = service.searchByWord("Grabowski");
        assertEquals(2, serviceList.size());
    }

    @Test
    public void shoudSaveExistingUser() {

        User userToSave = new User();
        userToSave.setId(7);
        userToSave.setName("Wojciech");

        repository.update(userToSave);

        verify(repository, times(1)).update(userToSave);

    }

    @Test
    public void shouldSearchById() {

        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(3);
        User user2 = new User();
        user2.setId(4);
        list.add(user1);
        list.add(user2);

        when(repository.searchById(3)).thenReturn(user1);

        List<User> serviceList = new ArrayList<>();
        serviceList.add(service.searchById(3));

        assertEquals(1,serviceList.size());
    }

    @Test
    public void shouldDeleteUser() {
        User userToDelete = new User();
        userToDelete.setId(8);
        userToDelete.setName("Adam");
        userToDelete.setSurname("Lis");
        userToDelete.setEmail("al@wp.pl");
        List<Book> borrowedList = new ArrayList<>();
        userToDelete.setBook(borrowedList);

        when(repository.searchById(8)).thenReturn(userToDelete);

        service.deleteById(8);

        verify(repository,times(1)).delete(userToDelete);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteUser() {
        User userToDelete = new User();
        userToDelete.setId(8);
        userToDelete.setName("Adam");
        userToDelete.setSurname("Lis");
        userToDelete.setEmail("al@wp.pl");
        List<Book> borrowedList = new ArrayList<>();
        borrowedList.add(new Book());
        borrowedList.add(new Book());
        userToDelete.setBook(borrowedList);

        when(repository.searchById(8)).thenReturn(userToDelete);

        service.deleteById(8);

        verify(repository,times(0)).delete(userToDelete);
    }




}