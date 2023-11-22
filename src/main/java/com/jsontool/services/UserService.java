package com.jsontool.services;

import com.jsontool.model.User;
import com.jsontool.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> getAll(User user) {
        return repository.findAll();
    }

}
