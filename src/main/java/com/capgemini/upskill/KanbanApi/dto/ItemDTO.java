package com.capgemini.upskill.KanbanApi.dto;

import java.util.UUID;


public record ItemDTO (
        UUID id,
        String title,
        String description,
        String state,
        String type,
        int estimation,
        String createdAt,
        UserDTO createdBy,
        UserDTO assignedTo,
        BoardDTO board
) {}

