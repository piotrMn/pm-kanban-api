package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.request.CreateTeamRequest;
import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.TeamDTO;
import com.capgemini.upskill.KanbanApi.mapper.TeamMapper;
import com.capgemini.upskill.KanbanApi.repository.TeamRepository;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TeamService extends BaseService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMapper teamMapper;


    public TeamService(TeamRepository teamRepository, UserRepository userRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
    }

    public List<TeamDTO> findAll() {
        List<Team> all = teamRepository.findAll();
        return teamMapper.toDTOs(all);
    }

    public TeamDTO getById(UUID teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(createException(Team.class, teamId));
        return teamMapper.toDTO(team);
    }

    public void saveTeam(CreateTeamRequest request) {
        User createdBy = userRepository.findByEmail(request.getCreatedByEmail()).orElseThrow();
        Team team = new Team();
        team.setName(request.getName());
        team.setCreatedBy(createdBy);
        for(UUID id : request.getMembersIds()) {
            User user = userRepository.findById(id).orElseThrow(createException(User.class, id));
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
