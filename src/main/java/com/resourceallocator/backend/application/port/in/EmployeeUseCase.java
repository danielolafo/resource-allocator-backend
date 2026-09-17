package com.resourceallocator.backend.application.port.in;

import com.resourceallocator.backend.application.dto.EmployeeDto;

import java.util.List;

public interface EmployeeUseCase {

    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    EmployeeDto create(EmployeeDto dto);

    EmployeeDto update(Long id, EmployeeDto dto);

    void delete(Long id);
}