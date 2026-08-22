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
public class ItemService extends BaseService {

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

    public List<ItemDTO> getFiltered(UUID teamId, ItemState state) {
        List<Item> items = itemRepository.getFiltered(teamId, state);
        return itemMapper.toDTOs(items);
    }

    public ItemDTO findById(UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(createException(Item.class, itemId));
        return itemMapper.toDTO(item);
    }

    public void saveItem(CreateItemRequest request) {
        Item item = new Item();
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setEstimation(request.getEstimation());
        item.setType(request.getType());
        item.setState(request.getState());
        UUID assignedToId = request.getAssignedToId();
        User assignedTo = userRepository.findById(assignedToId).orElseThrow(createException(User.class, assignedToId));
        item.setAssignedTo(assignedTo);
        UUID createdById = request.getCreatedById();
        User createdBy = userRepository.findById(createdById).orElseThrow(createException(User.class, createdById));
        item.setCreatedBy(createdBy);
        UUID teamId = request.getTeamId();
        Team team = teamRepository.findById(teamId).orElseThrow(createException(Team.class, teamId));
        item.setTeam(team);
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(UUID itemId, CreateItemRequest request) {
        Item item = itemRepository.findById(itemId).orElseThrow(createException(Item.class, itemId));
        if (request.getTitle() != null && !item.getTitle().equals(request.getTitle())) {
            item.setTitle(request.getTitle());
        }
        if (request.getDescription() != null && !item.getDescription().equals(request.getDescription())) {
            item.setDescription(request.getDescription());
        }
        if (request.getType() != null && !item.getType().equals(request.getType())) {
            item.setType(request.getType());
        }
        if (request.getState() != null && !item.getState().equals(request.getState())) {
            item.setState(request.getState());
        }
        if (request.getEstimation() != null && !item.getEstimation().equals(request.getEstimation())) {
            item.setEstimation(request.getEstimation());
        }
        if (request.getCreatedById() != null && !item.getCreatedBy().getId().equals(request.getCreatedById())) {
            User createdBy = userRepository.findById(request.getCreatedById()).orElseThrow(createException(User.class, request.getCreatedById()));
            item.setCreatedBy(createdBy);
        }
        if (request.getAssignedToId() != null && !item.getAssignedTo().getId().equals(request.getAssignedToId())) {
            User assignedTo = userRepository.findById(request.getAssignedToId()).orElseThrow(createException(User.class, request.getAssignedToId()));
            item.setAssignedTo(assignedTo);
        }
        if (request.getTeamId() != null && !item.getTeam().getId().equals(request.getTeamId())) {
            Team team = teamRepository.findById(request.getTeamId()).orElseThrow(createException(Team.class, request.getTeamId()));
            item.setTeam(team);
        }
    }

    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }

}
