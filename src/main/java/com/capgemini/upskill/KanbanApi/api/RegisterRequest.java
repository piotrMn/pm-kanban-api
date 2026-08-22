package com.capgemini.upskill.KanbanApi.api;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;

    private String password;

    private String name;

}
