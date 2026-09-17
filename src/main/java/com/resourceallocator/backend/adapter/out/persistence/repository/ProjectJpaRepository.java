package com.resourceallocator.backend.adapter.out.persistence.repository;

import com.resourceallocator.backend.adapter.out.persistence.entity.ProjectJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {

    @EntityGraph(attributePaths = "requiredTechnologies")
    @Override
    List<ProjectJpaEntity> findAll();

    @EntityGraph(attributePaths = "requiredTechnologies")
    @Override
    Optional<ProjectJpaEntity> findById(Long id);
}