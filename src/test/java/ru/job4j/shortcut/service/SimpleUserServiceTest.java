package ru.job4j.shortcut.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.shortcut.UrlShortCutApp;
import ru.job4j.shortcut.configuration.LiquibaseConfiguration;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UrlShortCutApp.class, LiquibaseConfiguration.class})
public class SimpleUserServiceTest {
    @Autowired
    public UserRepository users;
    @Autowired
    public SimpleUserService userService;

    @Test
    public void whenSaveUniqueField() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            User user1 = new User(1, "http://www.1.ru", "user1", "pass1");
            User user2 = new User(2, "http://www.2.ru", "user1", "pass2");
            users.save(user1);
            users.save(user2);
        });
    }

    @Test
    public void whenUseSaveService() {
        User user = new User();
        user.setUrl("http://example.ru");
        Optional<User> dbUser = userService.save(user);
        users.deleteById(dbUser.get().getId());
        assertNotNull(dbUser.get().getLogin());
        assertNotNull(dbUser.get().getPassword());
        assertEquals(user.getUrl(), dbUser.get().getUrl());
    }
}
