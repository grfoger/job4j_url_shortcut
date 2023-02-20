package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.dto.LinkDTO;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.service.LinkService;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convertPost(@Valid @RequestBody LinkDTO linkDTO) {
        String login;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
        } else {
            login = principal.toString();
        }
        linkDTO.setUserLogin(login);
        Optional<Link> link = linkService.findByUrl(linkDTO.getUrl());
        if (link.isEmpty()) {
            link = linkService.save(linkDTO);
        }
        return ResponseEntity.of(Optional.of(Map.ofEntries(
                Map.entry("code", link.get().getCode())
        )));
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> getLink(@PathVariable String code) {
        Link link;
        try {
            link = linkService.findByCode(code).get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
