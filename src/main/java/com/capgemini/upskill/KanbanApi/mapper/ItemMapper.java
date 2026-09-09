package com.capgemini.upskill.KanbanApi.mapper;

import com.capgemini.upskill.KanbanApi.domain.Item;
import com.capgemini.upskill.KanbanApi.domain.User;
import com.capgemini.upskill.KanbanApi.dto.ItemDTO;
import com.capgemini.upskill.KanbanApi.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    private final UserMapper userMapper;

    private final BoardMapper boardMapper;

    public ItemMapper(UserMapper userMapper, BoardMapper boardMapper) {
        this.userMapper = userMapper;
        this.boardMapper = boardMapper;
    }

    public ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getState().name(),
                item.getType().name(),
                item.getEstimation(),
                item.getCreatedAt().toString(),
                userMapper.toDTO(item.getCreatedBy()),
                item.getAssignedTo() != null ? userMapper.toDTO(item.getAssignedTo()) : null,
                boardMapper.toDTO(item.getBoard())
                );
    }

    public List<ItemDTO> toDTOs(List<Item> items) {
        return items.stream().map(this::toDTO).toList();
    }

}
