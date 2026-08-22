package com.capgemini.upskill.KanbanApi.request;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;

public record UpdateItemStateRequest(
   ItemState state
) {}
