package com.capgemini.upskill.KanbanApi.response;

import lombok.Data;

@Data
public class LoginUserResponse {

    private String jwt;

    private String userName;

    private String email;

    private String[] authorities;

    public LoginUserResponse(String jwt, String userName, String email, String[] authorities) {
        this.userName = userName;
        this.jwt = jwt;
        this.email = email;
        this.authorities = authorities;
    }

}
