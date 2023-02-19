package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.LinkDTO;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface LinkService {
    Optional<Link> findByUrl(String url);

    Optional<Link> save(LinkDTO link);

    Optional<Link> findByCode(String code);

    Map<String, String> findStatistic();
}
