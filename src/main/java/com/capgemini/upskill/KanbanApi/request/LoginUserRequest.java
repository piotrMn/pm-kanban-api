package com.capgemini.upskill.KanbanApi.request;

public record LoginUserRequest(
    String email,
    String password
){}
