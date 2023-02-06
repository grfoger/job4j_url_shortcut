package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.User;
import ru.job4j.shortcut.service.UserService;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public void registrationPost(@RequestBody User user) {
        userService.save(user);
    }

}
