package com.capgemini.upskill.KanbanApi.request;

import java.util.UUID;

public record UpdateItemAssignRequest(
   UUID assignTo
) {}
