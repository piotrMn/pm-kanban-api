package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Board;
import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import com.capgemini.upskill.KanbanApi.mapper.BoardMapper;
import com.capgemini.upskill.KanbanApi.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardService(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> getBoardsForTeam(UUID teamId) {
        List<Board> boards = boardRepository.findByTeamId(teamId);
        return boardMapper.toDTOs(boards);
    }

}
