package com.resourceallocator.backend.adapter.in.web;

import com.resourceallocator.backend.application.dto.EmployeeDto;
import com.resourceallocator.backend.application.port.in.EmployeeUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeUseCase.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeUseCase.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto create(@Valid @RequestBody EmployeeDto dto) {
        return employeeUseCase.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable Long id, @Valid @RequestBody EmployeeDto dto) {
        return employeeUseCase.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeUseCase.delete(id);
    }
}