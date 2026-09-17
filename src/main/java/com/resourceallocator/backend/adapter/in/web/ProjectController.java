package com.resourceallocator.backend.adapter.in.web;

import com.resourceallocator.backend.application.dto.ProjectDto;
import com.resourceallocator.backend.application.port.in.ProjectUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectUseCase projectUseCase;

    @GetMapping
    public List<ProjectDto> findAll() {
        return projectUseCase.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto findById(@PathVariable Long id) {
        return projectUseCase.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(@Valid @RequestBody ProjectDto dto) {
        return projectUseCase.create(dto);
    }

    @PutMapping("/{id}")
    public ProjectDto update(@PathVariable Long id, @Valid @RequestBody ProjectDto dto) {
        return projectUseCase.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectUseCase.delete(id);
    }
}