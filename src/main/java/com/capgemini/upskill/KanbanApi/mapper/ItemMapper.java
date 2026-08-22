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

    public ItemMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setState(item.getState().name());
        itemDTO.setType(item.getType().name());
        itemDTO.setEstimation(item.getEstimation());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setTitle(item.getTitle());
        itemDTO.setCreatedAt(item.getCreatedAt().toString());
        itemDTO.setTeamName(item.getTeam().getName());
        User createdBy = item.getCreatedBy();
        UserDTO userDTO = userMapper.toDTO(createdBy);
        itemDTO.setCreatedBy(userDTO);
        return itemDTO;
    }

    public List<ItemDTO> toDTOs(List<Item> items) {
        return items.stream().map(this::toDTO).toList();
    }

}
