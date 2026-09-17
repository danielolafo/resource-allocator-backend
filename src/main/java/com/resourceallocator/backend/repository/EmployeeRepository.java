package com.resourceallocator.backend.repository;

import com.resourceallocator.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}