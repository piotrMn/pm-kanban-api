package com.capgemini.upskill.KanbanApi.domain;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import com.capgemini.upskill.KanbanApi.domain.enums.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ItemType type;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ItemState state;

    private Integer estimation;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    public Item(UUID id, String title, String description, ItemType type, ItemState state, int estimation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.state = state;
        this.estimation = estimation;
    }



}
