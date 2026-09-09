package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Board;
import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {

    private final TeamMapper teamMapper;

    public BoardMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public BoardDTO toDTO(Board board) {
        return new BoardDTO(board.getId(), board.getName(), teamMapper.toDTO(board.getTeam()));
    }

    public List<BoardDTO> toDTOs(List<Board> boards) {
        return boards.stream().map(this::toDTO).toList();
    }

}
