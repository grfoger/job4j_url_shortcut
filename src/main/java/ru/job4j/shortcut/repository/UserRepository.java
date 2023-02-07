package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    Collection<User> findAll();
    Optional<User> findByUrl(String url);
    Optional<User> findByLogin(String login);
}
