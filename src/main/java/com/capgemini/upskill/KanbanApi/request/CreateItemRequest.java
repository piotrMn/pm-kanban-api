package com.capgemini.upskill.KanbanApi.request;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemType;

import java.util.UUID;

public record CreateItemRequest (
        String title,
        String description,
        Integer estimation,
        ItemType type,
        ItemState state,
        UUID createdById,
        UUID assignedToId,
        UUID teamId
){}
