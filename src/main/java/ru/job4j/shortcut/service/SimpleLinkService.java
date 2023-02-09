package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
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
        link.setCode(RandomString.make(LINK_LENGTH));
        /**
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.getAuthorities();
        users.findByLogin
         **/
        // TODO: 09.02.2023 добавить прикрепление ассоциированного юзера перед сохранением
        // TODO: 09.02.2023  перенести links.incrementCount(linkDb.getId()); в метод вызова адреса
        return Optional.of(links.save(link));
    }

}
