package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Comment;
import com.capgemini.upskill.KanbanApi.domain.Item;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.CommentDTO;
import com.capgemini.upskill.KanbanApi.mapper.CommentMapper;
import com.capgemini.upskill.KanbanApi.repository.CommentRepository;
import com.capgemini.upskill.KanbanApi.repository.ItemRepository;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import com.capgemini.upskill.KanbanApi.request.CreateCommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ItemRepository itemRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> findByItemId(UUID itemId) {
        List<Comment> byItemId = commentRepository.findByItemId(itemId);
        return commentMapper.toDTOs(byItemId);
    }

    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    public void saveComment(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.content());
        User author = userRepository.findById(UUID.fromString(request.authorId())).orElseThrow();
        comment.setAuthor(author);
        Item item = itemRepository.findById(UUID.fromString(request.itemId())).orElseThrow();
        comment.setItem(item);
        commentRepository.save(comment);
    }
}
