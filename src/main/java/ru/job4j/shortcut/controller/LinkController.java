package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.service.LinkService;
import ru.job4j.shortcut.service.UserService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convertPost(@RequestBody Link link) {
        Optional<Link> dbLink = linkService.findByUrl(link.getUrl());
        if(dbLink.isEmpty()) {
            dbLink = linkService.save(link);
        }
        return ResponseEntity.of(Optional.of(Map.ofEntries(
                Map.entry("code", dbLink.get().getCode())
        )));
    }

   // TODO: 10.02.2023 переписать респонс с мапы на стринг
}
