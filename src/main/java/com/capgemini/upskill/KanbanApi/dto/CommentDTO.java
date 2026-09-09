package com.capgemini.upskill.KanbanApi.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CommentDTO(

    UUID id,
    UserDTO author,
    String content,
    Timestamp createdAt
) {}
