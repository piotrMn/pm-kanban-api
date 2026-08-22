package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import com.capgemini.upskill.KanbanApi.request.LoginUserRequest;
import com.capgemini.upskill.KanbanApi.request.RegisterUserRequest;
import com.capgemini.upskill.KanbanApi.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserDTO userDTO;
        try {
            userDTO = userService.registerUser(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userDTO.toString());
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> loginUser(@RequestBody LoginUserRequest request) {
        String jwt;
        try {
            jwt = userService.loginUser(request);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } else {
            return ResponseEntity.ok(jwt);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
