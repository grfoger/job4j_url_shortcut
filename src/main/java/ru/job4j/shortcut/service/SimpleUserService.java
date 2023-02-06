package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleUserService implements UserService{

    private final UserRepository users;

    @Override
    public void save(User user) {
        users.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return users.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return users.findById(id);
    }
}
