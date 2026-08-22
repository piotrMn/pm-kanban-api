package com.capgemini.upskill.KanbanApi.repository;

import com.capgemini.upskill.KanbanApi.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {

    @Query(value = "select b from Board b inner join b.team t where t.id = :teamId")
    List<Board> findByTeamId(UUID teamId);

}
