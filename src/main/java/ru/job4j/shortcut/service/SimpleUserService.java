package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder encoder;

    private final static int LOGIN_LENGTH = 6;
    private final static int PASS_LENGTH = 8;

    @Override
    public Optional<User> save(User user) {

        // TODO: 13.02.2023 добавить проеврку на то что url нет в бд которая в слое контроллеров 
        user.setLogin(RandomString.make(LOGIN_LENGTH));
        String tempPass = RandomString.make(PASS_LENGTH);
        user.setPassword(encoder.encode(tempPass));
        User userDb = null;
        try {
            userDb = users.save(user);
        } catch (RuntimeException e) {
            save(user);
        }
        userDb.setPassword(tempPass);
        return Optional.of(userDb);
    }

    @Override
    public Optional<User> findByUrl(String url) {
        return users.findByUrl(url);
    }

    @Override
    public Collection<User> findAll() {
        return users.findAll();
    }

}
