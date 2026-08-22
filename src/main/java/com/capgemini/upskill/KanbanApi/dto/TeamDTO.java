package com.capgemini.upskill.KanbanApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TeamDTO {

    private UUID id;

    private String name;

    private UserDTO createdBy;

    private List<UserDTO> teamMembers;

}
