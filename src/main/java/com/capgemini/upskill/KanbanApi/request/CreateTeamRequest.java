package com.capgemini.upskill.KanbanApi.request;

import java.util.List;

public record CreateTeamRequest(
        String name,
        String createdByEmail,
        List<String> membersIds
){}
