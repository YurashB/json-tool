package com.jsontool.services;

import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        User userWithEncodedPassword = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        return repository.save(userWithEncodedPassword);
    }
}
