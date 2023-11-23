package com.jsontool.services;

import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        User userWithEncodedPassword = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        return repository.save(userWithEncodedPassword);
    }

    public User login(String email, String password) {
        String badRequestMessage = "Invalid email or password entered";

        User user = repository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequestMessage));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequestMessage);
        }

        return user;
    }
}
