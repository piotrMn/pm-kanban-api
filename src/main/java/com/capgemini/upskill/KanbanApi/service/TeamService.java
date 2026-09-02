package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.TeamDTO;
import com.capgemini.upskill.KanbanApi.mapper.TeamMapper;
import com.capgemini.upskill.KanbanApi.repository.TeamRepository;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import com.capgemini.upskill.KanbanApi.request.CreateTeamRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMapper teamMapper;


    public TeamService(TeamRepository teamRepository, UserRepository userRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
    }

    public List<TeamDTO> findAll() {
        List<Team> all = teamRepository.findAllByOrderByNameAsc();
        return teamMapper.toDTOs(all);
    }

    public List<String> getAllTeamNames() {
        return teamRepository.getAllTeamNames();
    }

    public TeamDTO getById(String teamId) {
        UUID uuid = UUID.fromString(teamId);
        Team team = teamRepository.findById(uuid).orElseThrow();
        return teamMapper.toDTO(team);
    }

    public TeamDTO findByName(String name) {
        Team team = teamRepository.findByName(name).orElseThrow();
        return teamMapper.toDTO(team);
    }

    public void saveTeam(CreateTeamRequest request) {
        User createdBy = userRepository.findByEmail(request.createdByEmail()).orElseThrow();
        Team team = new Team();
        team.setName(request.name());
        team.setCreatedBy(createdBy);
        for(String id : request.membersIds()) {
            UUID uuid = UUID.fromString(id);
            User user = userRepository.findById(uuid).orElseThrow();
            team.getUsers().add(user);
            user.getTeams().add(team);
        }
        teamRepository.save(team);
    }

    public List<TeamDTO> findTeamsByUser(UUID userId) {
        List<Team> byUserId = teamRepository.findByUserId(userId);
        return teamMapper.toDTOs(byUserId);
    }

}
