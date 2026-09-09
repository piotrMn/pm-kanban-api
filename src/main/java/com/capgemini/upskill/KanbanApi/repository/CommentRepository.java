package com.capgemini.upskill.KanbanApi.repository;

import com.capgemini.upskill.KanbanApi.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query(value = "select c from Comment c join c.item i where i.id = :itemId")
    List<Comment> findByItemId(UUID itemId);
}
