package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.dto.WipLimitDTO;
import com.capgemini.upskill.KanbanApi.service.WipLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/wip-limits")
public class WipLimitController {

    private final WipLimitService wipLimitService;

    public WipLimitController(WipLimitService wipLimitService) {
        this.wipLimitService = wipLimitService;
    }

    @GetMapping
    public List<WipLimitDTO> getAll() {
        return wipLimitService.getAllWipLimits();
    }

}
