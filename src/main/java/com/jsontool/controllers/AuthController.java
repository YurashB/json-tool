package com.jsontool.controllers;

import com.jsontool.dto.UserRequestDTO;
import com.jsontool.mappers.UserMapper;
import com.jsontool.model.User;
import com.jsontool.services.AuthService;
import com.jsontool.services.Login;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;
    private final UserMapper mapper;

    // TODO return else
    @PostMapping(value = "/register")
    public String register(@Valid @RequestBody UserRequestDTO userDTO) {
        return service.save(mapper.toUser(userDTO)).getEmail();
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
