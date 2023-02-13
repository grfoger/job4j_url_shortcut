package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SimpleLinkService implements LinkService{

    private final LinkRepository links;
    private final UserRepository users;

    private final static int LINK_LENGTH = 8;

    @Override
    public Optional<Link> findByUrl(String url) {
        return links.findByUrl(url);
    }

    @Override
    public Optional<Link> save(Link link) {

        // TODO: 13.02.2023 добавить проеврку на то что url нет в бд которая в слое контроллеров
        link.setCode(RandomString.make(LINK_LENGTH));
        String login;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        } else {
            login = principal.toString();
        }
        link.setUser(users.findByLogin(login).get());
        Link dblink = null;
        try {
            dblink = links.save(link);
        } catch (RuntimeException e) {
            save(link);
        }
        return Optional.of(dblink);
    }

    @Override
    public Optional<Link> findByCode(String code) {
        Optional<Link> linkDb = links.findByCode(code);
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
