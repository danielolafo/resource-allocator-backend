package com.resourceallocator.backend.application.port.out;

import com.resourceallocator.backend.domain.model.Technology;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository {

    List<Technology> findAll();

    Optional<Technology> findById(Long id);

    Optional<Technology> findByName(String name);

    Technology save(Technology technology);

    void deleteById(Long id);
}