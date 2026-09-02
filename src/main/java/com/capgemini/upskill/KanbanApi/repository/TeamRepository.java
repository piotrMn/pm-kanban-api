package com.capgemini.upskill.KanbanApi.repository;

import com.capgemini.upskill.KanbanApi.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    @Query(value = "select t from Team t inner join t.users u where u.id = :userId")
    List<Team> findByUserId(UUID userId);

    List<Team> findAllByOrderByNameAsc();
    @Query(value = "select t.name from Team t")
    List<String> getAllTeamNames();

    Optional<Team> findByName(String name);

}
