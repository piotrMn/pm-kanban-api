package com.capgemini.upskill.KanbanApi.service;

import com.capgemini.upskill.KanbanApi.domain.Board;
import com.capgemini.upskill.KanbanApi.domain.Item;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.dto.ItemDTO;
import com.capgemini.upskill.KanbanApi.mapper.ItemMapper;
import com.capgemini.upskill.KanbanApi.repository.BoardRepository;
import com.capgemini.upskill.KanbanApi.repository.ItemRepository;
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
    private final ItemMapper itemMapper;

    private final BoardRepository boardRepository;


    public ItemService(ItemRepository itemRepository, UserRepository userRepository, ItemMapper itemMapper, BoardRepository boardRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;
        this.boardRepository = boardRepository;
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
        User assignedTo = userRepository.findById(UUID.fromString(request.assignedTo())).orElseThrow();
        item.setAssignedTo(assignedTo);
        User createdBy = userRepository.findById(UUID.fromString(request.createdBy())).orElseThrow();
        item.setCreatedBy(createdBy);
        Board board = boardRepository.findById(UUID.fromString(request.boardId())).orElseThrow();
        item.setBoard(board);
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(UUID itemId, CreateItemRequest request) {
        Item item = itemRepository.findById(itemId).orElseThrow();
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
        if (request.createdBy() != null && !item.getCreatedBy().getId().toString().equals(request.createdBy())) {
            User createdBy = userRepository.findById(UUID.fromString(request.createdBy())).orElseThrow();
            item.setCreatedBy(createdBy);
        }
        if (request.assignedTo() != null && !item.getAssignedTo().getId().toString().equals(request.assignedTo())) {
            User assignedTo = userRepository.findById(UUID.fromString(request.assignedTo())).orElseThrow();
            item.setAssignedTo(assignedTo);
        }
        if (request.boardId() != null && !item.getBoard().getId().toString().equals(request.boardId())) {
            Board board = boardRepository.findById(UUID.fromString(request.boardId())).orElseThrow();
            item.setBoard(board);
        }
    }

    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }

}
