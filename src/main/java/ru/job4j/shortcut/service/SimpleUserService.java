package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
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

    private final static int LOGIN_LENGTH = 6;
    private final static int PASS_LENGTH = 8;

    @Override
    public Optional<User> save(User user) {
        int current = accountDataGenerator.getAndIncrement();
        user.setLogin(RandomString.make(LOGIN_LENGTH));
        user.setPassword(RandomString.make(PASS_LENGTH));
        return Optional.of(users.save(user));
    }

    @Override
    public Optional<User> findByUrl(String url) {
        return users.findByUrl(url);
    }

}
