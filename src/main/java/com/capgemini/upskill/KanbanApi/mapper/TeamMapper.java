package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.TeamDTO;
import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamMapper {

    private final UserMapper userMapper;

    public TeamMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TeamDTO toDTO(Team team) {
        return new TeamDTO(team.getId(), team.getName(), userMapper.toDTO(team.getCreatedBy()), team.getUsers()
                .stream().sorted(Comparator.comparing(User::getName)).
                map(userMapper::toDTO).toList());
    }

    public List<TeamDTO> toDTOs(List<Team> team) {
        return team.stream().map(this::toDTO).toList();
    }

}
