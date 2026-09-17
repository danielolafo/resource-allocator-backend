package com.resourceallocator.backend.application.service;

import com.resourceallocator.backend.application.dto.EmployeeDto;
import com.resourceallocator.backend.application.dto.EmployeeTechnologyDto;
import com.resourceallocator.backend.application.exception.BadRequestException;
import com.resourceallocator.backend.application.exception.ResourceNotFoundException;
import com.resourceallocator.backend.application.port.in.EmployeeUseCase;
import com.resourceallocator.backend.application.port.out.EmployeeRepository;
import com.resourceallocator.backend.application.port.out.TechnologyRepository;
import com.resourceallocator.backend.domain.model.Employee;
import com.resourceallocator.backend.domain.model.EmployeeTechnology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final TechnologyRepository technologyRepository;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Employee employee = toDomain(dto);
        employee.setId(null);
        return toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        getEntity(id);
        Employee employee = toDomain(dto);
        employee.setId(id);
        return toDto(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        getEntity(id);
        employeeRepository.deleteById(id);
    }

    private Employee getEntity(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id " + id));
    }

    private Employee toDomain(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setEmail(dto.email());
        employee.setPosition(dto.position());
        employee.setHireDate(dto.hireDate());
        employee.setCostPerDay(dto.costPerDay());
        if (dto.technologies() != null) {
            for (EmployeeTechnologyDto tech : dto.technologies()) {
                requireTechnology(tech.technologyId());
                employee.addTechnology(new EmployeeTechnology(
                        null,
                        tech.technologyId(),
                        tech.level(),
                        tech.version(),
                        tech.yearsExperience()));
            }
        }
        return employee;
    }

    private void requireTechnology(Long technologyId) {
        if (technologyRepository.findById(technologyId).isEmpty()) {
            throw new BadRequestException("Tecnolog\u00eda no encontrada con id " + technologyId);
        }
    }

    private EmployeeDto toDto(Employee employee) {
        List<EmployeeTechnologyDto> technologies = employee.getTechnologies().stream()
                .map(t -> new EmployeeTechnologyDto(
                        t.getTechnologyId(),
                        t.getLevel(),
                        t.getVersion(),
                        t.getYearsExperience()))
                .toList();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getHireDate(),
                employee.getCostPerDay(),
                technologies);
    }
}