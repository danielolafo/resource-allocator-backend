package com.resourceallocator.backend.adapter.out.persistence.mapper;

import com.resourceallocator.backend.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.resourceallocator.backend.domain.model.Employee;
import com.resourceallocator.backend.domain.model.EmployeeTechnology;

import java.util.List;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static void populate(EmployeeJpaEntity target, Employee source) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        target.setPosition(source.getPosition());
        target.setHireDate(source.getHireDate());
        target.setCostPerDay(source.getCostPerDay());
    }

    public static Employee toDomain(EmployeeJpaEntity jpa) {
        List<EmployeeTechnology> technologies = jpa.getTechnologies().stream()
                .map(t -> new EmployeeTechnology(
                        t.getId(),
                        t.getTechnology().getId(),
                        t.getLevel(),
                        t.getVersion(),
                        t.getYearsExperience()))
                .toList();

        Employee employee = new Employee();
        employee.setId(jpa.getId());
        employee.setFirstName(jpa.getFirstName());
        employee.setLastName(jpa.getLastName());
        employee.setEmail(jpa.getEmail());
        employee.setPosition(jpa.getPosition());
        employee.setHireDate(jpa.getHireDate());
        employee.setCostPerDay(jpa.getCostPerDay());
        employee.setTechnologies(technologies);
        return employee;
    }
}