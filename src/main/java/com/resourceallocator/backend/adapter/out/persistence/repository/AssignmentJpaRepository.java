package com.resourceallocator.backend.adapter.out.persistence.repository;

import com.resourceallocator.backend.adapter.out.persistence.entity.AssignmentJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignmentJpaRepository extends JpaRepository<AssignmentJpaEntity, Long> {

    @EntityGraph(attributePaths = {"employee", "project"})
    @Override
    List<AssignmentJpaEntity> findAll();

    @EntityGraph(attributePaths = {"employee", "project"})
    @Override
    Optional<AssignmentJpaEntity> findById(Long id);

    @Query("select count(distinct a.employee.id) "
            + "from AssignmentJpaEntity a "
            + "where a.project.id = :projectId and a.endDate >= :today")
    long countActiveEmployees(@Param("projectId") Long projectId, @Param("today") LocalDate today);
}