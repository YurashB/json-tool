package com.jsontool.controllers;

import com.jsontool.model.User;
import com.jsontool.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("/hello")
    public String greeting(){
        return "Hello World";
    }

    @GetMapping("")
    public List<User> getAll(@RequestBody User user) {
        return service.getAll(user);
    }
}
