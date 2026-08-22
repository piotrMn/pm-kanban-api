package com.capgemini.upskill.KanbanApi.request;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemType;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateItemRequest {

    private String title;

    private String description;

    private Integer estimation;

    private ItemType type;

    private ItemState state;

    private UUID createdById;

    private UUID assignedToId;

    private UUID teamId;

}
