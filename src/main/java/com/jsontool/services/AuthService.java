package com.jsontool.services;

import com.jsontool.errors.InvalidJWTTokenProvidedError;
import com.jsontool.errors.InvalidUserCredentialsError;
import com.jsontool.errors.UserAlreadyExistError;
import com.jsontool.errors.UserNotFoundError;
import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final String accessTokenSecret = "8AD7F3E5377DSSDSSDSDS87AA2CA7DB2F57FF29";
    private final String refreshTokenSecret = "84D7F3E53774SSDSS5SD587AA2CA7DD2F57FF29";

    public void save(User user) {
        if (isUserAlreadyExist(user.getEmail())) {
            throw new UserAlreadyExistError(user.getEmail());
        }

        User userWithEncodedPassword = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        User createdUser = repository.save(userWithEncodedPassword);
    }

    public Login login(String email, String password) {
        User user = repository.findUserByEmail(email)
                .orElseThrow(InvalidUserCredentialsError::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsError();
        }

        return Login.of(user.getId(), accessTokenSecret, refreshTokenSecret);
    }

    public User getUserFromToken(String token) {
        Long userId;

        try {
            userId = Token.from(token, accessTokenSecret);
            return repository.findById(userId)
                    .orElseThrow(UserNotFoundError::new);
        } catch (Exception e) {
            throw new InvalidJWTTokenProvidedError();
        }
    }

    private boolean isUserAlreadyExist(String email) {
        return repository.findUserByEmail(email).isPresent();
    }

    public Login refreshAccess(String refreshToken) {
        Long userId = Token.from(refreshToken, refreshTokenSecret);

        return Login.of(userId, accessTokenSecret, Token.of(refreshToken));
    }
}
