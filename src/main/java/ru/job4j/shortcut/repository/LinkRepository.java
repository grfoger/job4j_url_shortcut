package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    Optional<Link> findByUrl(String url);
}
