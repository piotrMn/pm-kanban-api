package com.capgemini.upskill.KanbanApi.repository;

import com.capgemini.upskill.KanbanApi.domain.WipLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WipLimitRepository extends JpaRepository<WipLimit, UUID> {

}
