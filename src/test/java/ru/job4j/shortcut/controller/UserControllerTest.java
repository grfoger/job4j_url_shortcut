package ru.job4j.shortcut.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.shortcut.UrlShortCutApp;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UrlShortCutApp.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private UserService service;

    @Test
    @WithMockUser
    public void whenSaveUser() throws Exception {
        User u = new User();
        u.setUrl("http://grfoger.ru");
        String request = "{\"url\":\"" + u.getUrl() + "\"}";
        Mockito.when(service.save(u)).thenReturn(Optional.of(new User(1, u.getUrl(), "123", "pass")));
        MvcResult result = mock.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"http://grfoger.ru\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("\"login\":\"123\""));
        assertTrue(content.contains("\"password\":\"pass\""));
    }
}
