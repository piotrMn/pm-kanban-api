package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.dto.CommentDTO;
import com.capgemini.upskill.KanbanApi.request.CreateCommentRequest;
import com.capgemini.upskill.KanbanApi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public List<CommentDTO> getItemComments(@RequestParam(name = "itemId", required = true) String itemId) {
        UUID uuid = UUID.fromString(itemId);
        return commentService.findByItemId(uuid);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestBody CreateCommentRequest request) {
        commentService.saveComment(request);
    }

    @DeleteMapping(path = "/{commentId}")
    public void deleteComment(@PathVariable String commentId) {
        UUID uuid = UUID.fromString(commentId);
        commentService.deleteComment(uuid);
    }


}
