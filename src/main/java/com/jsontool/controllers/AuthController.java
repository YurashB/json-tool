package com.jsontool.controllers;

import com.jsontool.model.User;
import com.jsontool.services.AuthService;
import com.jsontool.services.Login;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(value = "/register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        Login login = service.login(user.getEmail(), user.getPassword());
        // TODO Delete refresh_token and save one token only
        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        response.addCookie(cookie);

        return service.login(user.getEmail(), user.getPassword()).getAccessToken().getToken();
    }

    @GetMapping("/user")
    public User user(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        return user;
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return "Logout success";
    }
}
