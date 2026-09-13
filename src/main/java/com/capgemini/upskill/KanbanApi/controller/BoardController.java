package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import com.capgemini.upskill.KanbanApi.request.CreateBoardRequest;
import com.capgemini.upskill.KanbanApi.request.CreateTeamRequest;
import com.capgemini.upskill.KanbanApi.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody CreateBoardRequest request) {
        boardService.saveBoard(request);
    }

    @GetMapping(path = "/{teamId}")
    public List<BoardDTO> getBoardsForTeam(@PathVariable UUID teamId) {
        return boardService.getBoardsForTeam(teamId);
    }



}
