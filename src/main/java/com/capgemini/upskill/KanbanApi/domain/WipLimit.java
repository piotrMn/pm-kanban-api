package com.capgemini.upskill.KanbanApi.domain;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "wip_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WipLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private ItemState state;

    @Column(name = "max_items")
    private int maxItems;

}
