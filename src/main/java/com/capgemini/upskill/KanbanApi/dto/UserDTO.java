package com.capgemini.upskill.KanbanApi.dto;

import java.util.UUID;

public record UserDTO (
        UUID id,
        String name,
        String email
){}

