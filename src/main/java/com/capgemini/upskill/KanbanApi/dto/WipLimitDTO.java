package com.capgemini.upskill.KanbanApi.dto;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import lombok.Data;

import java.util.UUID;

@Data
public class WipLimitDTO {

    private UUID id;

    private ItemState state;

    private Integer maxItems;

    private String teamName;

    private UUID teamId;

}
