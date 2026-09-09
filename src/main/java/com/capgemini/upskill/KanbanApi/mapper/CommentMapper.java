package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Comment;
import com.capgemini.upskill.KanbanApi.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment.getId(), userMapper.toDTO(comment.getAuthor()), comment.getContent(), comment.getCreatedAt());
    }

    public List<CommentDTO> toDTOs(List<Comment> comments) {
        return comments.stream().map(this::toDTO).toList();
    }
}
