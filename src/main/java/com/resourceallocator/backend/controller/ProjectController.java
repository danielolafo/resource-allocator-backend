package com.resourceallocator.backend.controller;

import com.resourceallocator.backend.dto.ProjectDto;
import com.resourceallocator.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDto> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(@Valid @RequestBody ProjectDto dto) {
        return projectService.create(dto);
    }

    @PutMapping("/{id}")
    public ProjectDto update(@PathVariable Long id, @Valid @RequestBody ProjectDto dto) {
        return projectService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}