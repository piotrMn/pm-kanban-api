package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.repository.WipLimitRepository;
import org.springframework.stereotype.Service;

@Service
public class WipLimitService {

    private final WipLimitRepository wipLimitRepository;

    public WipLimitService(WipLimitRepository wipLimitRepository) {
        this.wipLimitRepository = wipLimitRepository;
    }
}
