package com.resourceallocator.backend.application.port.out;

import com.resourceallocator.backend.domain.model.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {

    List<Assignment> findAll();

    Optional<Assignment> findById(Long id);

    Assignment save(Assignment assignment);

    void deleteById(Long id);
}