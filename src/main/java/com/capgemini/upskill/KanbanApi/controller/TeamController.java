package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.request.CreateTeamRequest;
import com.capgemini.upskill.KanbanApi.dto.TeamDTO;
import com.capgemini.upskill.KanbanApi.service.TeamService;
import com.capgemini.upskill.KanbanApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/teams")
public class TeamController {

    private final TeamService teamService;
    private final UserService userService;

    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamDTO> getAllTeams() {
        return teamService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody CreateTeamRequest request) {
        teamService.saveTeam(request);
    }

    @GetMapping(path = "/{teamId}/members")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO getTeamById(@PathVariable UUID teamId) {
        return teamService.getById(teamId);
    }

    @PostMapping(path = "/{teamId}/members")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToTeam(@PathVariable UUID teamId, @RequestBody UUID userId) {
        userService.addUserToTeam(userId, teamId);
    }

    @DeleteMapping(path = "/{teamId}/members")
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromTeam(@PathVariable UUID teamId, @RequestBody UUID userId) {
        userService.removeUserFromTeam(userId, teamId);
    }

}
