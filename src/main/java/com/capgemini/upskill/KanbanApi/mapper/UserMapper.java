package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setHashedPassword(user.getPasswordHash());
        return userDTO;
    }

    public List<UserDTO> toDTOs(List<User> users) {
        return users.stream().map(this::toDTO).toList();
    }

}
