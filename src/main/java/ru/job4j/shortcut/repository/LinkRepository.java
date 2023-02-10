package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    Optional<Link> findByUrl(String url);

    @Transactional
    @Modifying
    @Query(value = "UPDATE links SET count = count + 1 where id = ?", nativeQuery = true)
    void incrementCount(int id);

    Optional<Link> findByCode(String code);
}
