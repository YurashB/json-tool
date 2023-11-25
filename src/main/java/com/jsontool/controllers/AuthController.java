package com.jsontool.controllers;

import com.jsontool.dto.UserRequestDTO;
import com.jsontool.mappers.UserMapper;
import com.jsontool.model.User;
import com.jsontool.services.AuthService;
import com.jsontool.services.Login;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/jsontool/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;
    private final UserMapper mapper;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody UserRequestDTO userDTO, HttpServletResponse response) {
        service.save(mapper.toUser(userDTO));

        return "Success";
    }


    @PostMapping(value = "/login")
    public String login(@RequestBody UserRequestDTO userRequestDTO, HttpServletResponse response) {
        User user = new User(userRequestDTO.getEmail(), userRequestDTO.getPassword());
        Login login = service.login(user.getEmail(), user.getPassword());


        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/jsontool/auth");

        response.addCookie(cookie);

        return login.getAccessToken().getToken();
    }

    @GetMapping(value = "/user")
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

    @PostMapping(value = "/refresh")
    public String refresh(@CookieValue("refresh_token") String refreshToken) {
        return service.refreshAccess(refreshToken).getAccessToken().getToken();
    }
}
