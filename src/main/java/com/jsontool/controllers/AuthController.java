package com.jsontool.controllers;

import com.jsontool.model.User;
import com.jsontool.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }

    @PostMapping(value = "/login")
    public User login(@RequestBody User user) {
        return service.login(user.getEmail(), user.getPassword());
    }
}
