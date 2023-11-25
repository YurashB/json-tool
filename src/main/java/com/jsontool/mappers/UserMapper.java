package com.jsontool.mappers;

import com.jsontool.dto.UserRequestDTO;
import com.jsontool.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRequestDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword());
    }

    public UserRequestDTO toDto(User user) {
        return new UserRequestDTO(user.getEmail(), user.getPassword());
    }
}
