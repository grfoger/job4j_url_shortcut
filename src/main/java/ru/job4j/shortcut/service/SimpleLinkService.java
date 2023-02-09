package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.repository.LinkRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleLinkService implements LinkService{

    private final LinkRepository links;

    private final static int LINK_LENGTH = 8;

    @Override
    public Optional<Link> findByUrl(String url) {
        return links.findByUrl(url);
    }

    @Override
    public Optional<Link> save(Link link) {
        // TODO: 09.02.2023 метод должен проверять наличие в бд такого url и если есть возвращать существующий
        // TODO: 09.02.2023 метод должен кодировать и возвращать код
        // TODO: 09.02.2023 метод должен инкрементировать счётчк
        /**
        Optional<Link> dbLink = linkService.findByUrl(link.getUrl());
        if(dbLink.isEmpty()) {
            dbLink = linkService.save(link);
        }
         **/
        return Optional.empty();
    }

}
