package com.capgemini.upskill.KanbanApi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemDTO {

    private UUID id;

    private String title;

    private String description;

    private String state;

    private String type;

    private int estimation;

    private String createdAt;

    private UserDTO createdBy;

    private String teamName;

}
