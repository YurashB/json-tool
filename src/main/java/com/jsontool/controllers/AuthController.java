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

    record RegisterResponse(String message){}
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody UserRequestDTO userDTO, HttpServletResponse response) {
        service.save(mapper.toUser(userDTO));

        return new RegisterResponse("Success");
    }


    record LoginResponse(String accessToken){}
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody UserRequestDTO userRequestDTO, HttpServletResponse response) {
        User user = new User(userRequestDTO.getEmail(), userRequestDTO.getPassword());
        Login login = service.login(user.getEmail(), user.getPassword());


        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/jsontool/auth");

        response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());
    }

    @GetMapping(value = "/user")
    public User user(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        return user;
    }

    record LogoutResponse(String message){}
    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        System.out.println(cookie.getValue());

        return new LogoutResponse("Logout succesfully");
    }

    record RefreshResponse(String accessToken){}
    @PostMapping(value = "/refresh")
    public RefreshResponse refresh(@CookieValue("refresh_token") String refreshToken) {
        return new RefreshResponse(service.refreshAccess(refreshToken).getAccessToken().getToken());
    }
}
