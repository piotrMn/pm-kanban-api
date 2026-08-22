package com.capgemini.upskill.KanbanApi.dto;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;

import java.util.UUID;

public record WipLimitDTO (
        UUID id,
        ItemState state,
        Integer maxItems,
        String teamName,
        UUID teamId
){}
