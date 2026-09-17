package com.resourceallocator.backend.application.port.in;

import com.resourceallocator.backend.application.dto.AssignmentDto;

import java.util.List;

public interface AssignmentUseCase {

    List<AssignmentDto> findAll();

    AssignmentDto findById(Long id);

    AssignmentDto create(AssignmentDto dto);

    AssignmentDto update(Long id, AssignmentDto dto);

    void delete(Long id);
}