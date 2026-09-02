package com.capgemini.upskill.KanbanApi.controller;


import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import com.capgemini.upskill.KanbanApi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
