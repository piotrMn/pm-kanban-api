package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Item;
import com.capgemini.upskill.KanbanApi.domain.Team;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.dto.ItemDTO;
import com.capgemini.upskill.KanbanApi.mapper.ItemMapper;
import com.capgemini.upskill.KanbanApi.repository.ItemRepository;
import com.capgemini.upskill.KanbanApi.repository.TeamRepository;
import com.capgemini.upskill.KanbanApi.repository.UserRepository;
import com.capgemini.upskill.KanbanApi.request.CreateItemRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ItemMapper itemMapper;


    public ItemService(ItemRepository itemRepository, UserRepository userRepository, TeamRepository teamRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDTO> getFiltered(UUID teamId, UUID boardId, ItemState state) {
        List<Item> items = itemRepository.getFiltered(teamId, boardId, state);
        return itemMapper.toDTOs(items);
    }

    public ItemDTO findById(UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return itemMapper.toDTO(item);
    }

    public void saveItem(CreateItemRequest request) {
        Item item = new Item();
        item.setTitle(request.title());
        item.setDescription(request.description());
        item.setEstimation(request.estimation());
        item.setType(request.type());
        item.setState(request.state());
        UUID assignedToId = request.assignedToId();
        User assignedTo = userRepository.findById(assignedToId).orElseThrow();
        item.setAssignedTo(assignedTo);
        UUID createdById = request.createdById();
        User createdBy = userRepository.findById(createdById).orElseThrow();
        item.setCreatedBy(createdBy);
        UUID teamId = request.teamId();
        Team team = teamRepository.findById(teamId).orElseThrow();
        item.setTeam(team);
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(UUID itemId, CreateItemRequest request) {
        Item item = itemRepository.findById(itemId).orElseThrow(createException(Item.class, itemId));
        if (request.title() != null && !item.getTitle().equals(request.title())) {
            item.setTitle(request.title());
        }
        if (request.description() != null && !item.getDescription().equals(request.description())) {
            item.setDescription(request.description());
        }
        if (request.type() != null && !item.getType().equals(request.type())) {
            item.setType(request.type());
        }
        if (request.state() != null && !item.getState().equals(request.state())) {
            item.setState(request.state());
        }
        if (request.estimation() != null && !item.getEstimation().equals(request.estimation())) {
            item.setEstimation(request.estimation());
        }
        if (request.createdById() != null && !item.getCreatedBy().getId().equals(request.createdById())) {
            User createdBy = userRepository.findById(request.createdById()).orElseThrow(createException(User.class, request.createdById()));
            item.setCreatedBy(createdBy);
        }
        if (request.assignedToId() != null && !item.getAssignedTo().getId().equals(request.assignedToId())) {
            User assignedTo = userRepository.findById(request.assignedToId()).orElseThrow(createException(User.class, request.assignedToId()));
            item.setAssignedTo(assignedTo);
        }
        if (request.teamId() != null && !item.getTeam().getId().equals(request.teamId())) {
            Team team = teamRepository.findById(request.teamId()).orElseThrow(createException(Team.class, request.teamId()));
            item.setTeam(team);
        }
    }

    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }

}
