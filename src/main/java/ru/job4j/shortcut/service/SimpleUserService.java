package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.repository.UserRepository;

import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Service
public class SimpleUserService implements UserService{

    private final UserRepository users;
    private final AtomicInteger accountDataGenerator = new AtomicInteger();

    @Override
    public Optional<User> save(User user) {
        int current = accountDataGenerator.getAndIncrement();
        user.setLogin("user" + current);
        user.setPassword("pass" + current);
        return Optional.of(users.save(user));
    }

    @Override
    public Optional<User> findByUrl(String url) {
        return users.findByUrl(url);
    }

}
