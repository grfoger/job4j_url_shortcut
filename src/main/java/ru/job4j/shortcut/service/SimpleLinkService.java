package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.LinkDTO;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SimpleLinkService implements LinkService {

    private final LinkRepository links;
    private final UserRepository users;

    private final static int LINK_LENGTH = 8;

    @Override
    public Optional<Link> findByUrl(String url) {
        return links.findByUrl(url);
    }

    @Override
    public Optional<Link> save(LinkDTO linkDTO) {
        Link link = new Link(0, linkDTO.getUrl(), null, users.findByLogin(linkDTO.getUserLogin()).get(), 0);
        for (int i = 0; i < 5; i++) {
            link.setCode(RandomString.make(LINK_LENGTH));
            try {
                link = links.save(link);
            } catch (DataIntegrityViolationException e) {
                continue;
            }
            break;
        }
        return Optional.of(link);
    }

    @Override
    public Optional<Link> findByCode(String code) {
        if (code == null || code.length() == 0) {
            throw new IllegalArgumentException("Wrong code type");
        }
        Optional<Link> linkDb = links.findByCode(code);
        if (linkDb.isEmpty()) {
            throw new NoSuchElementException("Can not find link for redirect. Please check your code.");
        }
        links.incrementCount(linkDb.get().getId());
        return linkDb;
    }

    @Override
    public Map<String, String> findStatistic() {
        ArrayList<Link> list = (ArrayList<Link>) links.findAll();
        return list.stream().collect(Collectors
                .toMap(link -> "url : " + link.getUrl(), link -> "total : " + link.getCount()));
    }

}
