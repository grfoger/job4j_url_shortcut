package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.service.LinkService;
import ru.job4j.shortcut.service.UserService;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
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

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> getLink(@PathVariable String code) {
        Link link;
        try {
            link = linkService.findByCode(code).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .location(URI.create(link.getUrl()))
                .build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<Map<String, String>> statistic() {
        return ResponseEntity.of(Optional.of(linkService.findStatistic()));
    }
}
