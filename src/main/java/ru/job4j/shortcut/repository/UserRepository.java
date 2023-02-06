package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
