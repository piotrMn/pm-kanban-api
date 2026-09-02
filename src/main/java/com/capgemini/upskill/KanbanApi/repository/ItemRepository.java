package com.capgemini.upskill.KanbanApi.repository;

import com.capgemini.upskill.KanbanApi.domain.Item;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query(value = "select i from Item i join i.team t join i.board b where t.id=coalesce(:teamId, t.id) and b.id=coalesce(:boardId, b.id) and i.state=coalesce(:state, i.state)")
    List<Item> getFiltered(UUID teamId, UUID boardId, ItemState state);

}
