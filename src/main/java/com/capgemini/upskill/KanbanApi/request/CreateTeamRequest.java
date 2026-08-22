package com.capgemini.upskill.KanbanApi.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateTeamRequest {

    private String name;

    private UUID createdById;

    private List<UUID> membersIds;

}
