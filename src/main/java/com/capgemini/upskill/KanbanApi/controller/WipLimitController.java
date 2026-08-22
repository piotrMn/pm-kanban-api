package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.domain.WipLimit;
import com.capgemini.upskill.KanbanApi.dto.WipLimitDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/wip-limits")
public class WipLimitController {

    @GetMapping
    public List<WipLimitDTO> getAll() {
        return null;
    }

}
