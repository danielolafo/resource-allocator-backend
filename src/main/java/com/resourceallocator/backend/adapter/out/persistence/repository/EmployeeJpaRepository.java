package com.resourceallocator.backend.adapter.out.persistence.repository;

import com.resourceallocator.backend.adapter.out.persistence.entity.EmployeeJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeJpaEntity, Long> {

    @EntityGraph(attributePaths = "technologies")
    @Override
    List<EmployeeJpaEntity> findAll();

    @EntityGraph(attributePaths = "technologies")
    @Override
    Optional<EmployeeJpaEntity> findById(Long id);
}