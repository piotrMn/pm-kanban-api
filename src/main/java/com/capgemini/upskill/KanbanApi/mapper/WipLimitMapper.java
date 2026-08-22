package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.WipLimit;
import com.capgemini.upskill.KanbanApi.dto.WipLimitDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WipLimitMapper {

    public WipLimitDTO toDTO(WipLimit wipLimit) {
        return new WipLimitDTO(wipLimit.getId(), wipLimit.getState(), wipLimit.getMaxItems(), wipLimit.getTeam().getName(), wipLimit.getTeam().getId());
    }

    public List<WipLimitDTO> toDTOs(List<WipLimit> wipLimits) {
        return wipLimits.stream().map(this::toDTO).toList();
    }

}
