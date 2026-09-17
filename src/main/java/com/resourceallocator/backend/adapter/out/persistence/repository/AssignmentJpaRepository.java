package com.resourceallocator.backend.adapter.out.persistence.repository;

import com.resourceallocator.backend.adapter.out.persistence.entity.AssignmentJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentJpaRepository extends JpaRepository<AssignmentJpaEntity, Long> {

    @EntityGraph(attributePaths = {"employee", "project"})
    @Override
    List<AssignmentJpaEntity> findAll();

    @EntityGraph(attributePaths = {"employee", "project"})
    @Override
    Optional<AssignmentJpaEntity> findById(Long id);
}