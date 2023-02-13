package ru.job4j.shortcut.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.shortcut.UrlShortCutApp;
import ru.job4j.shortcut.configuration.DataSourceConfiguration;
import ru.job4j.shortcut.configuration.LiquibaseConfiguration;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UrlShortCutApp.class, LiquibaseConfiguration.class})
public class SimpleUserServiceTest {
    @Autowired
    public UserRepository users;

    @Test (expected = RuntimeException.class)
    public void whenSaveUniqueField() {
        User user1 = new User(1,"www.1.ru", "user1", "pass");
        User user2 = new User(2,"www.2.ru", "user2", "pass");
        users.save(user1);
        users.save(user2);
    }
}
