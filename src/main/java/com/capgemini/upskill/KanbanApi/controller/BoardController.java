package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.dto.BoardDTO;
import com.capgemini.upskill.KanbanApi.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(path = "/{teamId}")
    public List<BoardDTO> getBoardsForTeam(@PathVariable UUID teamId) {
        return boardService.getBoardsForTeam(teamId);
    }

}
