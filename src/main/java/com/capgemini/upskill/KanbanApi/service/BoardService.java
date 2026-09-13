package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Board;
import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import com.capgemini.upskill.KanbanApi.mapper.BoardMapper;
import com.capgemini.upskill.KanbanApi.repository.BoardRepository;
import com.capgemini.upskill.KanbanApi.repository.TeamRepository;
import com.capgemini.upskill.KanbanApi.request.CreateBoardRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    private final TeamRepository teamRepository;

    public BoardService(BoardRepository boardRepository, BoardMapper boardMapper, TeamRepository teamRepository) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.teamRepository = teamRepository;
    }

    public List<BoardDTO> getBoardsForTeam(UUID teamId) {
        List<Board> boards = boardRepository.findByTeamId(teamId);
        return boardMapper.toDTOs(boards);
    }

    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boardMapper.toDTOs(boards);
    }

    public void saveBoard(CreateBoardRequest request) {
        Board board = new Board();
        board.setName(request.boardName());
        Team team = teamRepository.findByName(request.teamName()).orElseThrow();
        board.setTeam(team);
        boardRepository.save(board);
    }

}
