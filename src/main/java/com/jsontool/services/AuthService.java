package com.jsontool.services;

import com.jsontool.errors.InvalidJWTTokenProvidedError;
import com.jsontool.errors.UserNotFoundError;
import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    // TODO refactor with @Value("${app.....}")
    private final String accessTokenSecret = "8AD7F3E5377DSSDSSDSDS87AA2CA7DB2F57FF29";
    // TODO delete refresh token
    private final String refreshTokenSecret = "C7DD82D7EASASASAAS842BFA43B5DA2BEBD717";

    public User save(User user) {
        User userWithEncodedPassword = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        return repository.save(userWithEncodedPassword);
    }

    public Login login(String email, String password) {
        String badRequestMessage = "Invalid email or password entered";

        User user = repository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequestMessage));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            // TODO refactor to custom exception
            new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequestMessage);
        }

        // TODO User already exist with email

        return Login.of(user.getId(), accessTokenSecret, refreshTokenSecret);
    }

    public User getUserFromToken(String token) {
        // TODO add status code for bad JWT or user not found
        Long userId = 0L;

        try {
            userId = Token.from(token, accessTokenSecret);

        } catch (Exception e) {
            throw new InvalidJWTTokenProvidedError();
        }

        return repository.findById(userId)
                .orElseThrow(UserNotFoundError::new);
    }
}
