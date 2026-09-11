package com.capgemini.upskill.KanbanApi.response;

import lombok.Data;

@Data
public class LoginUserResponse {

    private String jwt;
    private String userName;
    private String email;
    private String id;
    private String[] authorities;

    public LoginUserResponse(String jwt, String userName, String email, String id, String[] authorities) {
        this.userName = userName;
        this.jwt = jwt;
        this.email = email;
        this.id = id;
        this.authorities = authorities;
    }

}
