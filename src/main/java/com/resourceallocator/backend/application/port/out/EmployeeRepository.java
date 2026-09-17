package com.resourceallocator.backend.application.port.out;

import com.resourceallocator.backend.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    void deleteById(Long id);
}