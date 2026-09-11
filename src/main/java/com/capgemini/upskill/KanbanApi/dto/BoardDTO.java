package com.capgemini.upskill.KanbanApi.dto;

import java.util.UUID;

public record BoardDTO(
        UUID id,
        String name,
        TeamDTO team

){}



