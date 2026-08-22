package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Board;
import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {

    public BoardDTO toDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setName(boardDTO.getName());
        boardDTO.setTeamName(board.getTeam().getName());
        boardDTO.setTeamId(board.getTeam().getId());
        return boardDTO;
    }

    public List<BoardDTO> toDTOs(List<Board> boards) {
        return boards.stream().map(this::toDTO).toList();
    }

}
