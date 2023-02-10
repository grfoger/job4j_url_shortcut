package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.service.UserService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registrationPost(@RequestBody User user) {
        Optional<User> dbUser = userService.findByUrl(user.getUrl());
        Boolean empty = dbUser.isEmpty();
        if(empty) {
            dbUser = userService.save(user);
        }
        return ResponseEntity.of(Optional.of(Map.ofEntries(
                Map.entry("registration", empty.toString()),
                Map.entry("login", dbUser.get().getLogin()),
                Map.entry("password", dbUser.get().getPassword())
        )));
    }
    // TODO: 10.02.2023 переписать респонс с мапы на стринг 
}
