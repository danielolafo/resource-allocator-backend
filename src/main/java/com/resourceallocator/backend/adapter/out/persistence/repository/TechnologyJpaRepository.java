package com.resourceallocator.backend.adapter.out.persistence.repository;

import com.resourceallocator.backend.adapter.out.persistence.entity.TechnologyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyJpaRepository extends JpaRepository<TechnologyJpaEntity, Long> {

    Optional<TechnologyJpaEntity> findByNameIgnoreCase(String name);
}