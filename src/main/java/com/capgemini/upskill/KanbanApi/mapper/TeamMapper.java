package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.TeamDTO;
import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMapper {

    private final UserMapper userMapper;

    public TeamMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TeamDTO toDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        List<UserDTO> userDTOS = team.getUsers().stream().map(userMapper::toDTO).toList();
        teamDTO.setTeamMembers(userDTOS);
        User createdBy = team.getCreatedBy();
        teamDTO.setCreatedBy(userMapper.toDTO(createdBy));
        return teamDTO;
    }

    public List<TeamDTO> toDTOs(List<Team> team) {
        return team.stream().map(this::toDTO).toList();
    }

}
