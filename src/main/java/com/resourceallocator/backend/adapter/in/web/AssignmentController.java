package com.resourceallocator.backend.adapter.in.web;

import com.resourceallocator.backend.application.dto.AssignmentDto;
import com.resourceallocator.backend.application.port.in.AssignmentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentUseCase assignmentUseCase;

    @GetMapping
    public List<AssignmentDto> findAll() {
        return assignmentUseCase.findAll();
    }

    @GetMapping("/{id}")
    public AssignmentDto findById(@PathVariable Long id) {
        return assignmentUseCase.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssignmentDto create(@Valid @RequestBody AssignmentDto dto) {
        return assignmentUseCase.create(dto);
    }

    @PutMapping("/{id}")
    public AssignmentDto update(@PathVariable Long id, @Valid @RequestBody AssignmentDto dto) {
        return assignmentUseCase.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        assignmentUseCase.delete(id);
    }
}