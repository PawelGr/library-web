package com.pg.library.controller;

import com.pg.library.model.User;
import com.pg.library.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    View mockView;

    MockMvc mockMvc;


//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = standaloneSetup(userController)
//                .setSingleView(mockView)
//                .build();
//    }

    @Test
    public void shouldList() throws Exception{
        List <User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        when(userService.list()).thenReturn(expectedUsers);

        mockMvc.perform(get("/search/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("list", expectedUsers))
                .andExpect(view().name("/user/search/list"));
    }

    @Test
    public void form() {
    }

    @Test
    public void save() {
    }

    @Test
    public void search() {
    }

    @Test
    public void borrowBook() {
    }
}