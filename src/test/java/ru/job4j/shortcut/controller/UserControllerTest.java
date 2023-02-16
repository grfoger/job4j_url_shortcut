package ru.job4j.shortcut.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.shortcut.UrlShortCutApp;
import ru.job4j.shortcut.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlShortCutApp.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser
    public void whenSaveUser() throws Exception {

    }

}
