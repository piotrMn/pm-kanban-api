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

    @Query(value = "select i from Item i inner join i.team t where t.id = coalesce(:id, t.id) and i.state = coalesce(:state, i.state)")
    List<Item> getFiltered(UUID id, ItemState state);

}
