package com.capgemini.upskill.KanbanApi.request;

public record CreateCommentRequest(

        String content,
        String authorId,
        String itemId

) {}
