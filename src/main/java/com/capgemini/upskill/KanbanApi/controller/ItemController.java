package com.capgemini.upskill.KanbanApi.controller;

import com.capgemini.upskill.KanbanApi.request.CreateItemRequest;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.dto.ItemDTO;
import com.capgemini.upskill.KanbanApi.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDTO> getItemsFiltered(
            @RequestParam(name = "teamId", required = false) UUID teamId,
            @RequestParam(name = "state", required = false) ItemState state
    ) {
        return itemService.getFiltered(teamId, state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItem(@RequestBody CreateItemRequest request) {
        itemService.saveItem(request);
    }

    @GetMapping(path = "/{id}")
    public ItemDTO getItemById(@PathVariable("id") UUID id) {
        return itemService.findById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestBody CreateItemRequest request, @PathVariable("id") UUID id) {
        itemService.updateItem(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable("id") UUID id) {
        itemService.deleteItem(id);
    }

    @PostMapping(path = "/{id}/assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignUserToItem(@RequestBody UUID userId, @PathVariable("id") UUID itemId ) {
        CreateItemRequest request = new CreateItemRequest();
        request.setAssignedToId(userId);
        itemService.updateItem(itemId, request);
    }

    @PostMapping(path = "/{id}/state")
    @ResponseStatus(HttpStatus.OK)
    public void updateItemState(@RequestBody ItemState state, @PathVariable("id") UUID itemId ) {
        CreateItemRequest request = new CreateItemRequest();
        request.setState(state);
        itemService.updateItem(itemId, request);
    }

    @PostMapping(path = "/{id}/estimation")
    @ResponseStatus(HttpStatus.OK)
    public void updateItemEstimation(@RequestBody Integer estimation, @PathVariable("id") UUID itemId ) {
        CreateItemRequest request = new CreateItemRequest();
        request.setEstimation(estimation);
        itemService.updateItem(itemId, request);
    }

}
