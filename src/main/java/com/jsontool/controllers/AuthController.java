package com.jsontool.controllers;

import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserRepository repository;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User user) {
        return repository.save(user);
    }
}
