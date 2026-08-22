package com.capgemini.upskill.KanbanApi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BoardDTO {

    private UUID id;

    private String name;

    private String teamName;

    private UUID teamId;

}
