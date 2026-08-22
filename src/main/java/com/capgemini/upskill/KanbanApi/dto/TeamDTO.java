package com.capgemini.upskill.KanbanApi.dto;

import java.util.List;
import java.util.UUID;

public record TeamDTO (
        UUID id,
        String name,
        UserDTO createdBy,
        List<UserDTO> teamMembers
){}

