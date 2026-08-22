package com.capgemini.upskill.KanbanApi.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterUserRequest {

    @NotBlank
    @Email
    private String email;

    @Size(min = 10, max = 100)
    private String password;

    @NotBlank
    private String name;

}
