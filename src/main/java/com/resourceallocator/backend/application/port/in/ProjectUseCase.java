package com.resourceallocator.backend.application.port.in;

import com.resourceallocator.backend.application.dto.ProjectDto;

import java.util.List;

public interface ProjectUseCase {

    List<ProjectDto> findAll();

    ProjectDto findById(Long id);

    ProjectDto create(ProjectDto dto);

    ProjectDto update(Long id, ProjectDto dto);

    void delete(Long id);
}