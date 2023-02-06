package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.User;

import java.util.Collection;
import java.util.Optional;

@Service
public interface UserService {
    void save(User user);
    Collection<User> findAll();
    Optional<User> findById(int id);
}
