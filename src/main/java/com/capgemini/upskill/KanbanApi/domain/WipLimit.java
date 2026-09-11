package com.capgemini.upskill.KanbanApi.domain;

import com.capgemini.upskill.KanbanApi.domain.enums.ItemState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
// import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

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

    @Enumerated(EnumType.STRING)
    // @JdbcType(PostgreSQLEnumJdbcType.class)
    private ItemState state;

    @Column(name = "max_items")
    private int maxItems;

}
