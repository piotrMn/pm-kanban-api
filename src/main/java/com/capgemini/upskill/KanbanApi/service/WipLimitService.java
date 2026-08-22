package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.WipLimit;
import com.capgemini.upskill.KanbanApi.dto.WipLimitDTO;
import com.capgemini.upskill.KanbanApi.mapper.WipLimitMapper;
import com.capgemini.upskill.KanbanApi.repository.WipLimitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WipLimitService {

    private final WipLimitRepository wipLimitRepository;

    private final WipLimitMapper wipLimitMapper;

    public WipLimitService(WipLimitRepository wipLimitRepository, WipLimitMapper wipLimitMapper) {
        this.wipLimitRepository = wipLimitRepository;
        this.wipLimitMapper = wipLimitMapper;
    }

    public List<WipLimitDTO> getAllWipLimits() {
        List<WipLimit> wipLimits = wipLimitRepository.findAll();
        return wipLimitMapper.toDTOs(wipLimits);
    }


}
