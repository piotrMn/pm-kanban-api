package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.request.LoginUserRequest;
import com.capgemini.upskill.KanbanApi.request.RegisterUserRequest;
import com.capgemini.upskill.KanbanApi.response.LoginUserResponse;
import com.capgemini.upskill.KanbanApi.service.UserService;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final Gson gson = new Gson();


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        try {
            userService.registerUser(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(201).body(gson.toJson("User registered successfully"));
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest request) {
        LoginUserResponse response;
        try {
            response = userService.loginUser(request);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginUserResponse(null, null, null, null, null));
        }
        return ResponseEntity.ok(response);
    }

}
